package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.ADTs.IStack;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.expressions.IExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.types.BoolType;
import com.assignment2.robi.models.values.BoolValue;
import com.assignment2.robi.models.values.IValue;

public class WhileStatement implements IStatement 
{
    private IExpression exp;
    private IStatement stmt;

    public WhileStatement(IExpression exp, IStatement stmt)
    {
        this.exp = exp;
        this.stmt = stmt;
    }

    public String toString()
    {
        return "while (" + this.exp.toString() + ") {" + this.stmt.toString() + "}";
    }

    public PrgState execute(PrgState state) throws MyException
    {
        try
        {
            IMap<String, IValue> symTable = state.getSymTable();
            IHeap heap = state.getHeap();
            IValue expVal = this.exp.evaluate(symTable, heap);
            
            if (!expVal.getType().equals(new BoolType()))
                throw new MyException("Expression is not boolean");

            BoolValue boolVal = (BoolValue)expVal;
            IStack<IStatement> stack = state.getStack();
            if (boolVal.getValue())
            {
                stack.push(this);
                stack.push(this.stmt);
            }
            return state;
        }
        catch (Exception e)
        {
            throw new MyException(e.getMessage());
        }
    }
}
