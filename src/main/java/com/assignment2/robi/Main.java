package com.assignment2.robi;
import com.assignment2.robi.controller.Controller;
import com.assignment2.robi.repository.IRepository;
import com.assignment2.robi.repository.MemRepository;
import com.assignment2.robi.view.View;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.statements.AssignStatement;
import com.assignment2.robi.models.statements.CompStatement;
import com.assignment2.robi.models.statements.IStatement;
import com.assignment2.robi.models.statements.PrintStatement;
import com.assignment2.robi.models.statements.VarDeclaration;
import com.assignment2.robi.models.types.IntType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;
import com.assignment2.robi.models.expressions.ValueExpression;
import com.assignment2.robi.models.expressions.VarExpression;
import com.assignment2.robi.models.ADTs.*;

public class Main {
    public static void main(String[] args)
    {
        IStatement program = new CompStatement(
            new VarDeclaration("a", new IntType()),
            new CompStatement(
                new AssignStatement("a", new ValueExpression(new IntValue(2))),
                new PrintStatement(new VarExpression("a")))
        );
        
        PrgState state = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), program);
        IRepository repo = new MemRepository();
        repo.add(state);
        Controller ctrl = new Controller(repo);
        View view = new View(ctrl);
        view.run();
    }
}