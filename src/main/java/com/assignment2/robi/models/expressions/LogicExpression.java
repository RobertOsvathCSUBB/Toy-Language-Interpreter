package com.assignment2.robi.models.expressions;

import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.types.BoolType;
import com.assignment2.robi.models.values.BoolValue;
import com.assignment2.robi.models.values.IValue;

public class LogicExpression implements IExpression 
{
    private IExpression left;
    private IExpression right;
    private String op;

    public LogicExpression(IExpression left, IExpression right, String op)
    {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public IValue evaluate(IMap<String, IValue> table) throws MyException
    {
        try {
            IValue v1, v2;
            v1 = left.evaluate(table);
            v2 = right.evaluate(table);
            if (!(v1.getType() instanceof BoolType) || !(v2.getType() instanceof BoolType))
                throw new MyException("Invalid expression: " + v1.toString() + " " + op + " " + v2.toString());
            if (this.op.equals("and")) {
                BoolValue res = new BoolValue(((BoolValue)v1).getValue() && ((BoolValue)v2).getValue());
                return res;
            }
            if (this.op.equals("or")) {
                BoolValue res = new BoolValue(((BoolValue)v1).getValue() || ((BoolValue)v2).getValue());
                return res;
            }
            throw new MyException("Invalid operator");
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    public String toString()
    {
        return left.toString() + " " + op + " " + right.toString();
    }    
}
