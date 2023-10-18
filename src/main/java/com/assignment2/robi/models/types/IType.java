package com.assignment2.robi.models.types;
import com.assignment2.robi.models.values.IValue;

public interface IType 
{
    boolean equals(Object another);
    String toString();
    IValue getDefaultValue();
}
