package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.ADTs.IStack;
import com.assignment2.robi.models.ADTs.MyStack;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.types.IType;

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
        IStack<IStatement> newStack = new MyStack<>();
        IMap<String, IValue> newSymTable = state.getSymTable().clone();
        PrgState newState = new PrgState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap(), stm);
        return newState;
    }

    public IMap<String, IType> typecheck(IMap<String, IType> typeEnv) throws MyException
    {
        stm.typecheck(typeEnv.clone());
        return typeEnv;
    }
}
