package com.assignment2.robi.models.statements;
import java.io.BufferedReader;
import java.io.IOException;

import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.types.IntType;
import com.assignment2.robi.models.types.StringType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;
import com.assignment2.robi.models.values.StringValue;

public class ReadStatement implements IStatement 
{
    private IExpression exp;
    private String varName;

    public ReadStatement(IExpression exp, String varName)
    {
        this.exp = exp;
        this.varName = varName;
    }

    public String toString()
    {
        return "readFile(" + this.exp.toString() + ", " + this.varName + ")";
    }

    public PrgState execute(PrgState state) throws MyException
    {
        IMap<String, IValue> symTable = state.getSymTable();
        if (!symTable.contains(this.varName)) {
            throw new MyException("Undefined variable");
        }

        IValue val = symTable.get(this.varName);
        if (!val.getType().equals(new IntType())) {
            throw new MyException("Variable is not of type int");
        }

        IValue expVal = this.exp.evaluate(state.getSymTable(), state.getHeap());
        if (!expVal.getType().equals(new StringType())) {
            throw new MyException("Expression is not of type string");
        }
        
        IValue filename = state.getSymTable().get(((StringValue)expVal).getValue());
        if (!filename.getType().equals(new StringType())) {
            throw new MyException("Filename is not of type string");
        }

        BufferedReader fileIn = state.getFileTable().get((StringValue)filename);
        if (fileIn == null) {
            throw new MyException("File not opened");
        }

        try {
            String line = fileIn.readLine();
            if (line == null) {
                symTable.update(this.varName, new IntType().getDefaultValue());
            }
            else {
                symTable.update(this.varName, new IntValue(Integer.parseInt(line)));
            }   
        }
        catch (IOException e) {
            throw new MyException("Error reading from file: " + e.getMessage());
        }

        return state;
    }
}
