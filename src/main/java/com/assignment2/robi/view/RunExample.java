package com.assignment2.robi.view;

import com.assignment2.robi.controller.Controller;

public class RunExample extends Command
{
    private Controller ctr;

    public RunExample(String key, String description, Controller ctr)
    {
        super(key, description);
        this.ctr = ctr;
    }

    @Override
    public void execute()
    {
        try {
            Integer prgStateId = Integer.parseInt(this.getKey());
            ctr.allStep(prgStateId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
