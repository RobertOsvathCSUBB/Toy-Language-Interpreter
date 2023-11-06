package com.assignment2.robi.models.ADTs;
import java.util.HashMap;

public class MyMap<T1, T2> implements IMap<T1, T2>
{
    private HashMap<T1, T2> map;

    public MyMap()
    {
        this.map = new HashMap<>();
    }

    public void add(T1 key, T2 value)
    {
        this.map.put(key, value);
    }

    public T2 get(T1 key)
    {
        return this.map.get(key);
    }

    public void update(T1 key, T2 value)
    {
        this.map.put(key, value);
    }

    public void remove(T1 key)
    {
        this.map.remove(key);
    }

    public Boolean contains(T1 key)
    {
        return this.map.containsKey(key);
    }

    public Integer size()
    {
        return this.map.size();
    }

    public Boolean isEmpty()
    {
        return this.map.isEmpty();
    }

    public String toString()
    {
        String res = "{ ";
        for (T1 key : this.map.keySet())
            res += key.toString() + " -> " + this.map.get(key).toString() + "; ";
        res += "}";
        return res;
    }

    public Iterable<T2> values()
    {
        return this.map.values();
    }
}
