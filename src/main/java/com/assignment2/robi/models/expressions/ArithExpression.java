package com.assignment2.robi.models.expressions;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.types.IntType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;

public class ArithExpression implements IExpression 
{
    private IExpression left;
    private IExpression right;
    private String op;
    
    public ArithExpression(IExpression left, IExpression right, String op)
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
            if (!(v1.getType() instanceof IntType) || !(v2.getType() instanceof IntType))
                throw new MyException("Invalid expression: " + v1.toString() + " " + op + " " + v2.toString());
            if (this.op.equals("+")) {
                IntValue res = new IntValue(((IntValue)v1).getValue() + ((IntValue)v2).getValue());
                return res;
            }
            if (this.op.equals("-")) {
                IntValue res = new IntValue(((IntValue)v1).getValue() - ((IntValue)v2).getValue());
                return res;
            }
            if (this.op.equals("*")) {
                IntValue res = new IntValue(((IntValue)v1).getValue() * ((IntValue)v2).getValue());
                return res;
            }
            if (this.op.equals("/")) {
                if (((IntValue)v2).getValue() == 0)
                    throw new MyException("Division by zero");
                IntValue res = new IntValue(((IntValue)v1).getValue() / ((IntValue)v2).getValue());
                return res;
            }
            throw new MyException("Invalid operator");
        } catch (Exception e) {
            throw new MyException("Invalid expression");
        }
    }

    public String toString()
    {
        return left.toString()+ " " + op + " " + right.toString();
    }
}
