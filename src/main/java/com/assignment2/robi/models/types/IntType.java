package com.assignment2.robi.models.types;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;

public class IntType implements IType 
{
    public boolean equals(Object another)
    {
        return another instanceof IntType;
    }

    public String toString()
    {
        return "int";
    }
    
    public IValue getDefaultValue()
    {
        return new IntValue(0);
    }
}
