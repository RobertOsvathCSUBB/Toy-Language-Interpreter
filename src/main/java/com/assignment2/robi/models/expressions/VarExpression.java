package com.assignment2.robi.models.expressions;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.values.IValue;

public class VarExpression implements IExpression 
{
    private String id;
    
    public VarExpression(String id)
    {
        this.id = id;
    }

    public IValue evaluate(IMap<String, IValue> table)
    {
        return table.get(id);
    }

    public String toString()
    {
        return id;
    }    
}
