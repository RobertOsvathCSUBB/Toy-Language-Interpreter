package com.assignment2.robi.models.types;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.StringValue;

public class StringType implements IType
{
    public boolean equals(Object another)
    {
        return another instanceof StringType;
    }

    public String toString()
    {
        return "string";
    }

    public IValue getDefaultValue()
    {
        return new StringValue("");
    }
}
