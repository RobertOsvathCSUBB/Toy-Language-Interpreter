package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.types.IType;

public class NOPStatement implements IStatement 
{
    public PrgState execute(PrgState state) throws MyException 
    {
        return null;
    }

    public String toString()
    {
        return "";
    }    

    public IMap<String, IType> typecheck(IMap<String, IType> typeEnv) throws MyException
    {
        return typeEnv;
    }
}
