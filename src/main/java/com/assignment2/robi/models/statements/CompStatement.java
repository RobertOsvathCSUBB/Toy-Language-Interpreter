package com.assignment2.robi.models.statements;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.ADTs.IStack;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.types.IType;

public class CompStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    public PrgState execute(PrgState state) throws MyException {
        IStack<IStatement> stk = state.getStack();
        stk.push(this.second);
        stk.push(this.first);
        return null;
    }

    public String toString() {
        return this.first.toString() + " | " + this.second.toString();
    }

    public IMap<String, IType> typecheck(IMap<String, IType> typeEnv) throws MyException {
        return this.second.typecheck(this.first.typecheck(typeEnv));
    }
}
