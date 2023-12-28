package com.assignment2.robi.models.expressions;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.types.IType;

public class VarExpression implements IExpression 
{
    private String id;
    
    public VarExpression(String id)
    {
        this.id = id;
    }

    public IValue evaluate(IMap<String, IValue> table, IHeap heap)
    {
        return table.get(id);
    }

    public String toString()
    {
        return id;
    }    

    public IType typecheck(IMap<String, IType> typeEnv)
    {
        return typeEnv.get(id);
    }
}
