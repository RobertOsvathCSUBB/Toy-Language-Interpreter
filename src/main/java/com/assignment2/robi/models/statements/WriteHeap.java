package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.RefValue;

public class WriteHeap implements IStatement
{
    private String varName;
    private IExpression exp;

    public WriteHeap(String varName, IExpression exp)
    {
        this.varName = varName;
        this.exp = exp;
    }

    public String toString()
    {
        return "wH(" + this.varName + ", " + this.exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException
    {
        IMap<String, IValue> symTable = state.getSymTable();
        if (!symTable.contains(this.varName))
            throw new MyException("Variable " + this.varName + " is not defined.");

        IValue varVal = symTable.get(this.varName);
        if (!(varVal instanceof RefValue))
            throw new MyException("Variable " + this.varName + " is not a reference.");

        Integer address = ((RefValue)varVal).getAddress();
        IHeap heap = state.getHeap();
        if (!heap.contains(address))
            throw new MyException("Address " + address + " not found in heap.");

        IValue expVal = this.exp.evaluate(symTable, heap);
        if (!expVal.getType().equals(((RefValue)varVal).getLocationType()))
            throw new MyException("Expression type and reference variable type do not match.");

        heap.update(address, expVal);
        return state;
    }
}
