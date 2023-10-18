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

    public PrgState getProgramState()
    {
        return this.repo.getCrtPrg();
    }

    public PrgState oneStep(PrgState state) throws MyException
    {
        IStack<IStatement> stk = state.getStack();
        if (stk.isEmpty())
            throw new MyException("Stack is empty!");
        IStatement crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep()
    {
        PrgState state = this.getProgramState();
        System.out.println(state.toString());
        try {
            while (!state.getStack().isEmpty())
            {
                this.oneStep(state);
                System.out.println(state.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
