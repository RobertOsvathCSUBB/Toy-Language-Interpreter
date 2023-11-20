package com.assignment2.robi.models.values;
import com.assignment2.robi.models.types.IType;
import com.assignment2.robi.models.types.RefType;

public class RefValue implements IValue 
{
    private Integer address;
    private IType locationType;

    public RefValue(Integer addr, IType loc)
    {
        this.address = addr;
        this.locationType = loc;
    }

    public Integer getAddress()
    {
        return this.address;
    }

    public void setAddress(Integer addr)
    {
        this.address = addr;
    }

    public IType getLocationType()
    {
        return this.locationType;
    }

    public IType getType()
    {
        return new RefType(this.locationType);
    }

    public String toString()
    {
        return "(" + this.address.toString() + ", " + this.locationType.toString() + ")";
    }
}
