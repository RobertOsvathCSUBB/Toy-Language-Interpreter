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
import com.assignment2.robi.models.types.IType;

public class IfStatement implements IStatement 
{
    private IExpression exp;
    private IStatement stmt1;
    private IStatement stmt2;
    
    public IfStatement(IExpression exp, IStatement stmt1, IStatement stmt2)
    {
        this.exp = exp;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    public String toString()
    {
        return "if (" + this.exp.toString() + ") then {" + this.stmt1.toString() + "} else {" + this.stmt2.toString() + "}";
    }

    public PrgState execute (PrgState state) throws MyException
    {
        try {
            IMap<String, IValue> symTable = state.getSymTable();
            IHeap heap = state.getHeap();
            IValue val = this.exp.evaluate(symTable, heap);
            if (!(val.getType() instanceof BoolType))
                throw new MyException("Expression is not a boolean.");
            BoolValue boolVal = (BoolValue)val;
            IStack<IStatement> stk = state.getStack();
            if (boolVal.getValue())
                stk.push(this.stmt1);
            else
                stk.push(this.stmt2);
            return null;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    public IMap<String, IType> typecheck(IMap<String, IType> typeEnv) throws MyException
    {
        IType typexp = this.exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType()))
        {
            this.stmt1.typecheck(typeEnv.clone());
            this.stmt2.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF isn't of type bool.");
    }
}
