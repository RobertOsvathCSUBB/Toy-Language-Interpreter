package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.ADTs.IStack;
import com.assignment2.robi.models.ADTs.MyStack;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.values.IValue;

public class ForkStatement implements IStatement 
{
    IStatement stm;
    
    public ForkStatement(IStatement stm)
    {
        this.stm = stm;
    }

    public String toString()
    {
        return "fork(" + this.stm.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException 
    {
        PrgState newState = new PrgState(null, null, state.getOut(), state.getFileTable(), state.getHeap(), stm);
        IStack<IStatement> newStack = new MyStack<>();
        newStack.push(stm);
        newState.setStack(newStack);
        IMap<String, IValue> newSymTable = state.getSymTable().clone();
        newState.setSymTable(newSymTable);
        return newState;
    }
}
