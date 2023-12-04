package com.assignment2.robi.repository;
import java.util.List;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;

public interface IRepository 
{
    void add(PrgState state);
    void logPrgStateExec(PrgState state) throws MyException;
    void setLogFile(String path);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> states);
}
