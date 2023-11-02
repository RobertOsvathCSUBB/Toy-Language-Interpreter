package com.assignment2.robi.models.statements;
import java.io.BufferedReader;

import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.types.StringType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.StringValue;

public class CloseStatement implements IStatement
{
    private IExpression exp;

    public CloseStatement(IExpression exp)
    {
        this.exp = exp;
    }

    public String toString()
    {
        return "closeRFile(" + this.exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException
    {
        IValue expVal = this.exp.evaluate(state.getSymTable());
        if (!expVal.getType().equals(new StringType())) {
            throw new MyException("Expression is not a string");
        }

        IValue filename = state.getSymTable().get(((StringValue)expVal).getValue());
        if (!filename.getType().equals(new StringType())) {
            throw new MyException("Filename is not a string");
        }

        IMap<StringValue, BufferedReader> fileTable = state.getFileTable();
        BufferedReader file = fileTable.get((StringValue)filename);
        if (file == null) {
            throw new MyException("File not opened");
        }

        try {
            file.close();
        }
        catch (Exception e) {
            throw new MyException("Error closing file: " + e.getMessage());
        }

        fileTable.remove((StringValue)expVal);

        return state;
    }
}
