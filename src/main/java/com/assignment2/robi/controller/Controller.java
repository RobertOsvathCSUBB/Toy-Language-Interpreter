package com.assignment2.robi.controller;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.repository.IRepository;
import com.assignment2.robi.models.statements.IStatement;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.RefValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.ADTs.IStack;
import com.assignment2.robi.models.ADTs.MyHeap;
import com.assignment2.robi.models.ADTs.MyMap;

public class Controller 
{
    private IRepository repo;

    public Controller(IRepository r)
    {
        this.repo = r;
    }

    public void setLogFile(String path)
    {
        this.repo.setLogFile(path);
    }

    public PrgState getProgramStateByIndex(Integer index)
    {
        this.repo.setCrtPrg(index);
        return this.repo.getCrtPrg();
    }

    public PrgState oneStep(PrgState state) throws MyException
    {
        IStack<IStatement> stk = state.getStack();
        if (stk.isEmpty())
            throw new MyException("Stack is empty!");
        IStatement crtStmt = stk.pop();
        PrgState newState = crtStmt.execute(state);
        System.out.println(newState.toString());
        this.repo.logPrgStateExec();
        return newState;
    }

    public void allStep(Integer index)
    {
        PrgState state = this.getProgramStateByIndex(index - 1);
        System.out.println(state.toString());
        Integer step = 1;
        try {
            this.repo.logPrgStateExec();
            while (!state.getStack().isEmpty())
            {
                System.out.println(" ---------- Step: " + step.toString() + " ---------\n");
                this.oneStep(state);
                step += 1;
                state.getHeap().setContent(this.unsafeGarbageCollector(this.getAddrFromSymTable(state.getSymTable()), state.getHeap()));
            }
            state.setSymTable(new MyMap<>());
            state.getHeap().setContent(this.unsafeGarbageCollector(this.getAddrFromSymTable(state.getSymTable()), state.getHeap()));
            System.out.println(" ---------- After cleaning up: ---------\n");
            System.out.println(state.toString());
            this.repo.logPrgStateExec();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    List<Integer> getAddrFromSymTable(IMap<String, IValue> symTable)
    {
        Collection<IValue> values = symTable.values();
        return values.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue)v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    IHeap unsafeGarbageCollector(List<Integer> symTableAddr, IHeap heap)
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
}
