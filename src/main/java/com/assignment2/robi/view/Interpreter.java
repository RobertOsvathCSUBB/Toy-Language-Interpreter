package com.assignment2.robi.view;
import java.io.BufferedReader;
import com.assignment2.robi.controller.Controller;
import com.assignment2.robi.models.ADTs.MyHeap;
import com.assignment2.robi.models.ADTs.MyList;
import com.assignment2.robi.models.ADTs.MyMap;
import com.assignment2.robi.models.ADTs.MyStack;
import com.assignment2.robi.models.expressions.ArithExpression;
import com.assignment2.robi.models.expressions.ReadHeap;
import com.assignment2.robi.models.expressions.RelationalExpression;
import com.assignment2.robi.models.expressions.ValueExpression;
import com.assignment2.robi.models.expressions.VarExpression;
import com.assignment2.robi.models.state.PrgState;
import com.assignment2.robi.models.statements.AssignStatement;
import com.assignment2.robi.models.statements.CloseStatement;
import com.assignment2.robi.models.statements.CompStatement;
import com.assignment2.robi.models.statements.IStatement;
import com.assignment2.robi.models.statements.IfStatement;
import com.assignment2.robi.models.statements.NewStatement;
import com.assignment2.robi.models.statements.OpenStatement;
import com.assignment2.robi.models.statements.PrintStatement;
import com.assignment2.robi.models.statements.ReadStatement;
import com.assignment2.robi.models.statements.VarDeclaration;
import com.assignment2.robi.models.statements.WriteHeap;
import com.assignment2.robi.models.types.BoolType;
import com.assignment2.robi.models.types.IntType;
import com.assignment2.robi.models.types.RefType;
import com.assignment2.robi.models.types.StringType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;
import com.assignment2.robi.models.values.StringValue;
import com.assignment2.robi.repository.IRepository;
import com.assignment2.robi.repository.MemRepository;

public class Interpreter 
{
    public static void interpreterMain()
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
                new AssignStatement("a",
                    new RelationalExpression(new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(3)), "<")),
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

        IStatement program4 = new CompStatement(
            new VarDeclaration("varf", new StringType()),
            new CompStatement(
                new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                new CompStatement(
                    new OpenStatement(new VarExpression("varf")),
                    new CompStatement(
                        new VarDeclaration("varc", new IntType()),
                        new CompStatement(
                            new ReadStatement(new ValueExpression(new StringValue("varf")), "varc"),
                            new CompStatement(
                                new PrintStatement(new VarExpression("varc")),
                                new CompStatement(
                                    new ReadStatement(new ValueExpression(new StringValue("varf")), "varc"),
                                    new CompStatement(
                                        new PrintStatement(new VarExpression("varc")),
                                        new CloseStatement(new ValueExpression(new StringValue("varf")))
                                    )
                                )
                            )
                        )
                    )
                )
            )
        );

        IStatement program5 = new CompStatement(
            new VarDeclaration("v", new RefType(new IntType())),
            new CompStatement(
                new NewStatement("v", new ValueExpression(new IntValue(20))),
                new CompStatement(
                    new PrintStatement(new ReadHeap(new VarExpression("v"))),
                    new CompStatement(
                        new WriteHeap("v", new ValueExpression(new IntValue(30))),
                        new PrintStatement(new ArithExpression(
                            new ReadHeap(new VarExpression("v")),
                            new ValueExpression(new IntValue(5)),
                            "+"
                            )
                        )
                    )
                )
            )
        );

        IStatement program6 = new CompStatement(
            new VarDeclaration("v", new RefType(new IntType())),
            new CompStatement(
                new NewStatement("v", new ValueExpression(new IntValue(20))),
                new CompStatement(
                    new VarDeclaration("a", new RefType(new RefType(new IntType()))),
                    new CompStatement(
                        new NewStatement("a", new VarExpression("v")),
                        new CompStatement(
                            new NewStatement("v", new ValueExpression(new IntValue(30))),
                            new PrintStatement(new ReadHeap(new ReadHeap(new VarExpression("a"))))
                        )
                    )
                )
            )
        );
        
        PrgState state1 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program1);
        PrgState state2 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program2);
        PrgState state3 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program3);
        PrgState state4 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program4);
        PrgState state5 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program5);
        PrgState state6 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program6);



        IRepository repo = new MemRepository();
        repo.add(state1);
        repo.add(state2);
        repo.add(state3);
        repo.add(state4);
        repo.add(state5);
        repo.add(state6);
        Controller ctrl = new Controller(repo);
        ctrl.setLogFile("program.log");

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", program1.toString(), ctrl));
        menu.addCommand(new RunExample("2", program2.toString(), ctrl));
        menu.addCommand(new RunExample("3", program3.toString(), ctrl));
        menu.addCommand(new RunExample("4", program4.toString(), ctrl));
        menu.addCommand(new RunExample("5", program5.toString(), ctrl));
        menu.addCommand(new RunExample("6", program6.toString(), ctrl));
        menu.show();
    }    
}
