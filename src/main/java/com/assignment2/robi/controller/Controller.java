package com.assignment2.robi.controller;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.repository.IRepository;
import com.assignment2.robi.models.statements.IStatement;
import com.assignment2.robi.models.ADTs.IStack;

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
            while (!state.getStack().isEmpty())
            {
                System.out.println(" ---------- Step: " + step.toString() + " ---------\n");
                this.oneStep(state);
                step += 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
