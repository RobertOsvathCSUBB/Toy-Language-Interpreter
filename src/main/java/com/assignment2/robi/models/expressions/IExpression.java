package com.assignment2.robi.models.expressions;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.types.IType;

public interface IExpression
{
    IValue evaluate(IMap<String, IValue> table, IHeap heap) throws MyException;
    IType typecheck(IMap<String, IType> typeEnv) throws MyException;
}