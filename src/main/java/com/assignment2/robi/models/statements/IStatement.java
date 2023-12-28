package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.types.IType;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;

public interface IStatement
{
    PrgState execute(PrgState state) throws MyException;
    IMap<String, IType> typecheck(IMap<String, IType> typeEnv) throws MyException;
}
