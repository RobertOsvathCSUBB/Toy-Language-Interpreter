package com.assignment2.robi.models.values;
import com.assignment2.robi.models.types.IType;
import com.assignment2.robi.models.types.IntType;

public class IntValue implements IValue 
{
    private Integer value;
    
    public IntValue()
    {
        this.value = 0;
    }

    public IntValue(int value)
    {
        this.value = value;
    }

    public Integer getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return Integer.toString(this.value);
    }

    public IType getType()
    {
        return new IntType();
    }
}
