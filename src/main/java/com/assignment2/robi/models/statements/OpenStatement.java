package com.assignment2.robi.models.statements;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.types.StringType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.StringValue;

public class OpenStatement implements IStatement
{
    private IExpression exp;

    public OpenStatement(IExpression exp)
    {
        this.exp = exp;
    }

    public String toString()
    {
        return "openRFile(" + this.exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException
    {
        try {
            IValue expVal = this.exp.evaluate(state.getSymTable());
            if (!expVal.getType().equals(new StringType())) {
                throw new MyException("Expression is not a string");
            }

            IMap<StringValue, BufferedReader> fileTable = state.getFileTable();
            if (fileTable.contains((StringValue) expVal)) {
                throw new MyException("File already opened");
            }

            try {
                BufferedReader file = new BufferedReader(new FileReader(((StringValue) expVal).getValue()));
                fileTable.add((StringValue) expVal, file);
            } 
            catch (FileNotFoundException e) {
                throw new MyException("File does not exist: " + e.getMessage());
            } 
        }
        catch (MyException e) {
            throw new MyException(e.getMessage());
        }
        
        return state;
    }
}
