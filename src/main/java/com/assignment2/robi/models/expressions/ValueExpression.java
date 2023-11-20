package com.assignment2.robi.models.expressions;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.values.IValue;

public class ValueExpression implements IExpression 
{
    private IValue value;
    
    public ValueExpression(IValue value)
    {
        this.value = value;
    }

    public IValue evaluate(IMap<String, IValue> table, IHeap heap) 
    {
        return value;
    }

    public String toString()
    {
        return value.toString();
    }
}
