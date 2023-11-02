package com.assignment2.robi;
import com.assignment2.robi.controller.Controller;
import com.assignment2.robi.repository.IRepository;
import com.assignment2.robi.repository.MemRepository;
import com.assignment2.robi.view.View;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.statements.AssignStatement;
import com.assignment2.robi.models.statements.CompStatement;
import com.assignment2.robi.models.statements.IStatement;
import com.assignment2.robi.models.statements.IfStatement;
import com.assignment2.robi.models.statements.PrintStatement;
import com.assignment2.robi.models.statements.VarDeclaration;
import com.assignment2.robi.models.types.BoolType;
import com.assignment2.robi.models.types.IntType;
import com.assignment2.robi.models.values.BoolValue;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;
import com.assignment2.robi.models.expressions.ArithExpression;
import com.assignment2.robi.models.expressions.ValueExpression;
import com.assignment2.robi.models.expressions.VarExpression;
import com.assignment2.robi.models.ADTs.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        IStatement program1 = new CompStatement(
            new VarDeclaration("v", new IntType()),
            new CompStatement(
                new AssignStatement("v", new ValueExpression(new IntValue(2))),
                new PrintStatement(new VarExpression("v")))
        );

        IStatement program2 = new CompStatement(
            new VarDeclaration("a", new IntType()),
            new CompStatement(
                new AssignStatement("a",
                    new ArithExpression(
                        new ValueExpression(new IntValue(2)),
                        new ArithExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), "*"),
                        "+")),
                new CompStatement(
                    new VarDeclaration("b", new IntType()),
                    new CompStatement(
                        new AssignStatement("b", 
                            new ArithExpression(
                                new ArithExpression(
                                    new VarExpression("a"),
                                    new ArithExpression(
                                        new ValueExpression(new IntValue(4)),
                                        new ValueExpression(new IntValue(2)),
                                        "/"),
                                    "-"),
                                new ValueExpression(new IntValue(7)),
                                "+")
                            ),
                        new PrintStatement(new VarExpression("b"))
                    )
                )
                )
        );

        IStatement program3 = new CompStatement(
            new VarDeclaration("a", new BoolType()),
            new CompStatement(
                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                new CompStatement(
                    new VarDeclaration("v", new IntType()),
                    new CompStatement(
                        new IfStatement(
                            new VarExpression("a"),
                            new AssignStatement("v", new ValueExpression(new IntValue(2))),
                            new AssignStatement("v", new ValueExpression(new IntValue(3)))
                        ),
                        new PrintStatement(new VarExpression("v"))
                    )
                )
            )
        );
        
        PrgState state1 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), program1);
        PrgState state2 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), program2);
        PrgState state3 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), program3);

        IRepository repo = new MemRepository();
        repo.add(state1);
        repo.add(state2);
        repo.add(state3);
        Controller ctrl = new Controller(repo);
        View view = new View(ctrl);
        view.run();
    }
}