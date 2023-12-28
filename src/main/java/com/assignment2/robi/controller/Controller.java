package com.assignment2.robi.controller;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.repository.IRepository;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.RefValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.ADTs.MyHeap;
import java.util.concurrent.Executors;

public class Controller 
{
    private IRepository repo;
    private ExecutorService executor;
    private Integer stepCount;

    public Controller(IRepository r)
    {
        this.repo = r;
        this.stepCount = 1;
    }

    private void oneStepForAll(List<PrgState> states) throws MyException
    {
        System.out.println("\n----- Before step " + this.stepCount + " -----\n");
        states.forEach(prg -> {
            try {
                System.out.println(prg.toString());
                this.repo.logPrgStateExec(prg);
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        });

        List<Callable<PrgState>> callList = states.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        try {
            List<PrgState> newStates = executor.invokeAll(callList).stream()
                                                                .map( future -> {
                                                                    try {
                                                                        return future.get();
                                                                    }
                                                                    catch (Exception e) {
                                                                        System.out.println(e.getMessage());
                                                                        return null;
                                                                    }
                                                                })
                                                                .filter(p -> p != null)
                                                                .collect(Collectors.toList());
            states.addAll(newStates);
            System.out.println("\n----- After step " + this.stepCount + " -----\n");
            states.forEach(prg -> {
                try {
                    System.out.println(prg.toString());
                    this.repo.logPrgStateExec(prg);
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
            });
            this.stepCount++;
            this.repo.setPrgList(states);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    public void allStep()
    {
        this.executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = this.removeCompletedPrg(this.repo.getPrgList());
        while (!prgList.isEmpty())
        {
            IHeap heap = this.repo.getPrgList().get(0).getHeap();
            heap.setContent(this.garbageCollector(this.getAddrFromSymTables(), heap));
            try {
                this.oneStepForAll(prgList);
            } catch (MyException e) {
                System.out.println(e.getMessage());
                break;
            }
            prgList = this.removeCompletedPrg(this.repo.getPrgList());
        }
        this.executor.shutdownNow();
        this.repo.setPrgList(prgList);
    }

    private List<Integer> getAddrFromSymTables()
    {
        List<PrgState> states = this.repo.getPrgList();
        List<IValue> values = states.stream()
                .map(PrgState::getSymTable)
                .map(IMap::values)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        return values.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue)v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    private IHeap garbageCollector(List<Integer> symTableAddr, IHeap heap)
    {
        Map<Integer, IValue> newHeapMap = heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        IHeap newHeap = new MyHeap();
        newHeapMap.forEach( (Integer k, IValue v) -> {
            try {
                newHeap.add(k, v);
            } catch (MyException e) {}
        });
        return newHeap;
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> states)
    {
        return states.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
}
