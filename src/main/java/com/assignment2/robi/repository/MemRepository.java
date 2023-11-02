package com.assignment2.robi.repository;
import java.util.ArrayList;
import com.assignment2.robi.models.exception.MyException;
import com.assignment2.robi.models.state.PrgState;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

public class MemRepository implements IRepository 
{
    private ArrayList<PrgState> states;
    private Integer crtPrg;
    private String logFilePath;

    public MemRepository()
    {
        this.states = new ArrayList<PrgState>();
    }

    public void setLogFile(String path)
    {
        this.logFilePath = path;
    }

    public void setCrtPrg(Integer index)
    {
        this.crtPrg = index;
    }

    public void add(PrgState state)
    {
        this.states.add(state);
    }

    public PrgState getCrtPrg()
    {
        return this.states.get(this.crtPrg);
    }

    public void logPrgStateExec() throws MyException
    {
        try {
            // ask what's up with this, why it's not working
            // File file = new File(this.logFilePath);
            // file.delete();
            PrintWriter logFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            PrgState crtPrg = this.getCrtPrg();
            logFileWriter.println(crtPrg.toString());
            logFileWriter.close();
        }
        catch (IOException e){
            throw new MyException("IOException: " + e.getMessage());
        }
    }
}
