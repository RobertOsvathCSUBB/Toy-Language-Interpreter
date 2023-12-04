package com.assignment2.robi.view;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
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
import com.assignment2.robi.models.statements.ForkStatement;
import com.assignment2.robi.models.statements.IStatement;
import com.assignment2.robi.models.statements.IfStatement;
import com.assignment2.robi.models.statements.NewStatement;
import com.assignment2.robi.models.statements.OpenStatement;
import com.assignment2.robi.models.statements.PrintStatement;
import com.assignment2.robi.models.statements.ReadStatement;
import com.assignment2.robi.models.statements.VarDeclaration;
import com.assignment2.robi.models.statements.WhileStatement;
import com.assignment2.robi.models.statements.WriteHeap;
import com.assignment2.robi.models.types.BoolType;
import com.assignment2.robi.models.types.IntType;
import com.assignment2.robi.models.types.RefType;
import com.assignment2.robi.models.types.StringType;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.IntValue;
import com.assignment2.robi.models.values.StringValue;
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

        IStatement program7 = new CompStatement(
            new VarDeclaration("a", new IntType()),
            new CompStatement(
                new AssignStatement("a", new ValueExpression(new IntValue(1))),
                new WhileStatement(
                    new RelationalExpression(new VarExpression("a"), new ValueExpression(new IntValue(4)), "<="),
                    new CompStatement(
                        new PrintStatement(new VarExpression("a")),
                        new AssignStatement("a", new ArithExpression(new VarExpression("a"), new ValueExpression(new IntValue(1)), "+"))
                    )
                )
            )
        );

        IStatement program8 = new CompStatement(
                new VarDeclaration("v", new IntType()),
                new CompStatement(
                    new VarDeclaration("a", new RefType(new IntType())),
                    new CompStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(10))),
                        new CompStatement(
                            new NewStatement("a", new ValueExpression(new IntValue(22))),
                            new CompStatement(
                                new ForkStatement(
                                    new CompStatement(
                                        new WriteHeap("a", new ValueExpression(new IntValue(30))),
                                        new CompStatement(
                                            new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                            new CompStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new PrintStatement(new ReadHeap(new VarExpression("a")))
                                            )
                                        )
                                    )
                                ),
                                new CompStatement(
                                    new PrintStatement(new VarExpression("v")),
                                    new PrintStatement(new ReadHeap(new VarExpression("a")))
                                )
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
        PrgState state7 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program7);
        PrgState state8 = new PrgState(new MyStack<IStatement>(), new MyMap<String, IValue>(), new MyList<IValue>(), new MyMap<StringValue, BufferedReader>(), new MyHeap(), program8);

        List<MemRepository> repoList = new ArrayList<MemRepository>();
        for (int i = 0; i < 8; i++)
        {
            repoList.add(new MemRepository());
            repoList.get(i).setLogFile("program" + Integer.toString(i + 1) + ".txt");
        }

        repoList.get(0).add(state1);
        repoList.get(1).add(state2);
        repoList.get(2).add(state3);
        repoList.get(3).add(state4);
        repoList.get(4).add(state5);
        repoList.get(5).add(state6);
        repoList.get(6).add(state7);
        repoList.get(7).add(state8);

        List<Controller> ctrlList = new ArrayList<Controller>();
        for (int i = 0; i < 8; i++)
        {
            ctrlList.add(new Controller(repoList.get(i)));
        }

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", program1.toString(), ctrlList.get(0)));
        menu.addCommand(new RunExample("2", program2.toString(), ctrlList.get(1)));
        menu.addCommand(new RunExample("3", program3.toString(), ctrlList.get(2)));
        menu.addCommand(new RunExample("4", program4.toString(), ctrlList.get(3)));
        menu.addCommand(new RunExample("5", program5.toString(), ctrlList.get(4)));
        menu.addCommand(new RunExample("6", program6.toString(), ctrlList.get(5)));
        menu.addCommand(new RunExample("7", program7.toString(), ctrlList.get(6)));
        menu.addCommand(new RunExample("8", program8.toString(), ctrlList.get(7)));
        menu.show();
    }    
}
