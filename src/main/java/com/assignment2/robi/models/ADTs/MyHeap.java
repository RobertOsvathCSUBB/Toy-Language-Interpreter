package com.assignment2.robi.models.ADTs;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.values.IValue;
import java.util.HashMap;

public class MyHeap implements IHeap, IMap<Integer, IValue>
{
    private HashMap<Integer, IValue> data;
    private Integer nextFree;

    public MyHeap()
    {
        this.data = new HashMap<>();
        this.nextFree = 1;
    }

    public Integer nextFree()
    {
        return this.nextFree;
    }

    public void add(Integer key, IValue value) throws MyException
    {
        if (this.contains(key))
            throw new MyException("Key already exists in heap");
        this.data.put(key, value);
        this.nextFree++;
    }

    public IValue get(Integer key)
    {
        return this.data.get(key);
    }

    public void update(Integer key, IValue value)
    {
        this.data.put(key, value);
    }

    public void remove(Integer key)
    {
        this.data.remove(key);
    }

    public Boolean contains(Integer key)
    {
        return this.data.containsKey(key);
    }

    public Integer size()
    {
        return this.data.size();
    }

    public Boolean isEmpty()
    {
        return this.data.isEmpty();
    }

    public Iterable<IValue> values()
    {
        return this.data.values();
    }

    public String toString()
    {
        String res = "{ ";
        for (Integer key : this.data.keySet())
            res += key.toString() + " -> " + this.data.get(key).toString() + "; ";
        res += "}";
        return res;
    }
}
