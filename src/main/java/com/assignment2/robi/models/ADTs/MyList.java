package com.assignment2.robi.models.ADTs;
import java.util.ArrayList;

public class MyList<T> implements IList<T>
{
    private ArrayList<T> list;

    public MyList()
    {
        this.list = new ArrayList<T>();
    }

    public void add(T elem)
    {
        this.list.add(elem);
    }

    public T getElem(int index)
    {
        return this.list.get(index);
    }

    public void setElem(int index, T elem)
    {
        this.list.set(index, elem);
    }

    public void remove(int index)
    {
        this.list.remove(index);
    }

    public Integer size()
    {
        return this.list.size();
    }

    public Boolean isEmpty()
    {
        return this.list.isEmpty();
    }

    public String toString()
    {
        String res = "[ ";
        for (T elem : this.list)
            res += elem.toString() + ", ";
        res += "]";
        return res;
    }
}
