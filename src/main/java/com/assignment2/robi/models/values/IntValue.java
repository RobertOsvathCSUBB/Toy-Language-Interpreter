package com.assignment2.robi.models.values;
import com.assignment2.robi.models.types.IType;
import com.assignment2.robi.models.types.IntType;

public class IntValue implements IValue 
{
    private Integer value;

    public IntValue(int value)
    {
        this.value = value;
    }

    @Override
    public Object clone()
    {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new IntValue(this.value);
        }
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
