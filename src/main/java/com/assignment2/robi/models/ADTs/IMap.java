package com.assignment2.robi.models.ADTs;
import java.util.Collection;
import java.util.Set;
import java.util.Map.Entry;
import com.assignment2.robi.models.exception.MyException;

public interface IMap<T1, T2>
{
    void add(T1 key, T2 value) throws MyException;
    T2 get(T1 key);
    void update(T1 key, T2 value);
    void remove(T1 key);
    Boolean contains(T1 key);
    Integer size();
    Boolean isEmpty();
    Collection<T2> values();
    Set<Entry<T1, T2>> entrySet();
}
