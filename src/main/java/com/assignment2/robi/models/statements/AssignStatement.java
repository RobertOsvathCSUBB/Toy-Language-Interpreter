package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.values.IValue;

public class AssignStatement implements IStatement 
{
    private String id;
    private IExpression exp;
    
    public AssignStatement(String id, IExpression exp)
    {
        this.id = id;
        this.exp = exp;
    }

    public String toString()
    {
        return this.id + " = " + this.exp.toString() + "";
    }

    public PrgState execute(PrgState state) throws MyException
    {
        IMap<String, IValue> symTable = state.getSymTable();
        if (!symTable.contains(this.id))
            throw new MyException("Variable " + this.id + " is not defined.");
        try {
            IValue oldVal = symTable.get(this.id);
            IValue newVal = this.exp.evaluate(symTable);
            if (!oldVal.getType().equals(newVal.getType()))
                throw new MyException("Declared type of variable " + this.id + " and type of the assigned expression do not match.");
            symTable.update(this.id, newVal);
            return state;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }
}
