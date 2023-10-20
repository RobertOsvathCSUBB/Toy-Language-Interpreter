package com.assignment2.robi.repository;
import java.util.ArrayList;
import com.assignment2.robi.models.state.PrgState;

public class MemRepository implements IRepository 
{
    private ArrayList<PrgState> states;

    public MemRepository()
    {
        this.states = new ArrayList<PrgState>();
    }

    public void add(PrgState state)
    {
        this.states.add(state);
    }

    public PrgState getPrgByIndex(Integer index)
    {
        return this.states.get(index);
    }

    public PrgState getCrtPrg()
    {
        return new PrgState(null, null, null, null);
    }
}
