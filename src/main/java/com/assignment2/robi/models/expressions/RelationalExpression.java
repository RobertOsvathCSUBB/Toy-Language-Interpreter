package com.assignment2.robi.models.expressions;
import com.assignment2.robi.models.ADTs.IHeap;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.types.IntType;
import com.assignment2.robi.models.values.BoolValue;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;
import com.assignment2.robi.models.types.BoolType;
import com.assignment2.robi.models.types.IType;

public class RelationalExpression implements IExpression
{
    private IExpression left;
    private IExpression right;
    private String op;

    public RelationalExpression(IExpression left, IExpression right, String op)
    {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public IValue evaluate(IMap<String, IValue> table, IHeap heap) throws MyException
    {
        try {
            IValue leftVal = this.left.evaluate(table, heap);
            IValue rightVal = this.right.evaluate(table, heap);
            if (!leftVal.getType().equals(new IntType()) || !rightVal.getType().equals(new IntType()))
            {
                throw new MyException("Invalid relational expression");
            }
            int leftInt = ((IntValue)leftVal).getValue();
            int rightInt = ((IntValue)rightVal).getValue();
            switch (this.op)
            {
                case "<":
                {
                    return new BoolValue(leftInt < rightInt);
                }
                case ">":
                {
                    return new BoolValue(leftInt > rightInt);
                }
                case "<=":
                {
                    return new BoolValue(leftInt <= rightInt);
                }
                case ">=":
                {
                    return new BoolValue(leftInt >= rightInt);
                }
                case "==":
                {
                    return new BoolValue(leftInt == rightInt);
                }
                case "!=":
                {
                    return new BoolValue(leftInt != rightInt);
                }
                default:
                {
                    throw new MyException("Invalid relational operator");
                }
            }
        }   
        catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    public String toString()
    {
        return this.left.toString() + " " + this.op + " " + this.right.toString();
    }

    public IType typecheck(IMap<String, IType> typeEnv) throws MyException
    {
            IType type1, type2;
            type1 = left.typecheck(typeEnv);
            type2 = right.typecheck(typeEnv);
            if (type1.equals(new IntType()))
            {
                if (type2.equals(new IntType()))
                {
                    return new BoolType();
                }
                else
                {
                    throw new MyException("Second operand is not an integer");
                }
            }
            else 
            {
                throw new MyException("First operand is not an integer");
            }
    }
}
