package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IList;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.values.IValue;

public class PrintStatement implements IStatement {
    private IExpression exp;

    public PrintStatement(IExpression e)
    {
        this.exp = e;
    }

    public PrgState execute(PrgState state) throws MyException 
    {
        IList<IValue> out = state.getOut();
        try {
            IMap<String, IValue> symTable = state.getSymTable();
            IValue val = this.exp.evaluate(symTable);
            out.add(val);
            return state;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    public String toString()
    {
        return "print(" + this.exp.toString() + ");";
    }
}
