package com.assignment2.robi.view;
import com.assignment2.robi.controller.Controller;

public class View 
{
    private Controller controller;
    
    public View(Controller c)
    {
        this.controller = c;
    }

    public void run()
    {
        this.controller.allStep();
    }
}
