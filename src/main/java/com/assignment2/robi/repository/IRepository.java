package com.assignment2.robi.repository;
import com.assignment2.robi.models.state.PrgState;

public interface IRepository {
    void add(PrgState state);
    PrgState getCrtPrg();
    PrgState getPrgByIndex(Integer index);
}
