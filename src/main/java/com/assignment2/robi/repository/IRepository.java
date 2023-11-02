package com.assignment2.robi.repository;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;

public interface IRepository 
{
    void add(PrgState state);
    PrgState getCrtPrg();
    void setCrtPrg(Integer index);
    void logPrgStateExec() throws MyException;
    void setLogFile(String path);
}
