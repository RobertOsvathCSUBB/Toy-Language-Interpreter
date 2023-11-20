package com.assignment2.robi.models.ADTs;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.values.IValue;

public interface IHeap 
{
    Integer nextFree();
    void add(Integer key, IValue value) throws MyException;
    IValue get(Integer key);
    void update(Integer key, IValue value);
    void remove(Integer key);
    Boolean contains(Integer key);
    Integer size();
    Boolean isEmpty();
    Iterable<IValue> values();
}
