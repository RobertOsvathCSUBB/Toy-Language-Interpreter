package com.assignment2.robi.models.ADTs;

public interface IMap<T1, T2>
{
    void add(T1 key, T2 value);
    T2 get(T1 key);
    void update(T1 key, T2 value);
    void remove(T1 key);
    Boolean contains(T1 key);
    Integer size();
    Boolean isEmpty();
    Iterable<T2> values();
}
