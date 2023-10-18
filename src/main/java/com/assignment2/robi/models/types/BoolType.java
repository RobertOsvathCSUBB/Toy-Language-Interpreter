package com.assignment2.robi.models.types;
import com.assignment2.robi.models.values.BoolValue;
import com.assignment2.robi.models.values.IValue;

public class BoolType implements IType
{
    public boolean equals(Object another)
    {
        return another instanceof BoolType;
    }

    public String toString()
    {
        return "bool";
    }

    public IValue getDefaultValue()
    {
        return new BoolValue(false);
    }
}
