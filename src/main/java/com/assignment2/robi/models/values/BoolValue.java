package com.assignment2.robi.models.values;
import com.assignment2.robi.models.types.IType;
import com.assignment2.robi.models.types.BoolType;

public class BoolValue implements IValue 
{
    private Boolean value;
    
    public BoolValue()
    {
        this.value = false;
    }

    public BoolValue(Boolean value)
    {
        this.value = value;
    }

    public Boolean getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return Boolean.toString(this.value);
    }

    public IType getType()
    {
        return new BoolType();
    }
}
