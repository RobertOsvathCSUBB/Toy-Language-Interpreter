package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;

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
}
