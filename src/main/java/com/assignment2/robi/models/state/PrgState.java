package com.assignment2.robi.models.state;
import com.assignment2.robi.models.statements.IStatement;
import com.assignment2.robi.models.ADTs.*;
import com.assignment2.robi.models.values.IValue;
import com.assignment2.robi.models.values.StringValue;
import java.io.BufferedReader;

public class PrgState
{
    private IStack<IStatement> exeStack;
    private IMap<String, IValue> symTable;
    private IList<IValue> out;
    private IMap<StringValue, BufferedReader> fileTable;
    private IStatement originalPrg;
    

    public PrgState(IStack<IStatement> stk, IMap<String, IValue> map, IList<IValue> out, IMap<StringValue, BufferedReader> ft, IStatement prg) {
        this.exeStack = stk;
        this.symTable = map;
        this.out = out;
        this.fileTable = ft;
        this.originalPrg = prg;
        this.exeStack.push(prg);
    }

    public IStack<IStatement> getStack() {
        return this.exeStack;
    }

    public void setStack(IStack<IStatement> stk) {
        this.exeStack = stk;
    }

    public IMap<String, IValue> getSymTable() {
        return this.symTable;
    }

    public void setSymTable(IMap<String, IValue> map) {
        this.symTable = map;
    }

    public IList<IValue> getOut() {
        return this.out;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    public IMap<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public void setFileTable(IMap<StringValue, BufferedReader> ft) {
        this.fileTable = ft;
    }

    public IStatement getOriginalPrg() {
        return this.originalPrg;
    }

    public void setOriginalPrg(IStatement prg) {
        this.originalPrg = prg;
    }

    public String toString()
    {
        return "ExeStack:\n" + this.exeStack.toString() + "\nSymTable:\n" + this.symTable.toString() + "\nOut:\n" + this.out.toString() + "\nFileTable:\n" + this.fileTable.toString() + "\n\n";
    }
}