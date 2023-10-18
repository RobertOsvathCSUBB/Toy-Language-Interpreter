package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.exception.MyException;

public interface IStatement
{
    PrgState execute(PrgState state) throws MyException;
}
