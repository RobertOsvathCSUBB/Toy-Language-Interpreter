package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.types.RefType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.RefValue;

public class NewStatement implements IStatement 
{
    private String var;
    private IExpression expr;

    public NewStatement(String var, IExpression expr)
    {
        this.var = var;
        this.expr = expr;
    }

    public PrgState execute(PrgState state) throws MyException
    {
        IMap<String, IValue> symTable = state.getSymTable();
        if (!symTable.contains(var))
            throw new MyException("Variable not declared");
        
        IHeap heap = state.getHeap();
        IValue exprVal = this.expr.evaluate(symTable, heap);
        IValue varVal = symTable.get(var);
        if (!varVal.getType().equals(new RefType(exprVal.getType())))
            throw new MyException("Variable and expression types do not match");

        Integer addr = heap.nextFree();
        heap.add(addr, exprVal);
        ((RefValue)varVal).setAddress(addr);
        symTable.update(var, varVal);

        return null;
    }

    public String toString()
    {
        return "new(" + this.var + ", " + this.expr.toString() + ")";
    }
}
