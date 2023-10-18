package com.assignment2.robi.models.expressions;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.ADTs.IMap;

public interface IExpression
{
    IValue evaluate(IMap<String, IValue> table) throws MyException;
}