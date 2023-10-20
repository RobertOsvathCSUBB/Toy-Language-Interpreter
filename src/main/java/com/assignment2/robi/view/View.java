package com.assignment2.robi.view;
import com.assignment2.robi.controller.Controller;
import com.assignment2.robi.models.state.PrgState;

import java.util.Scanner;

public class View 
{
    private Controller controller;
    
    public View(Controller c)
    {
        this.controller = c;
    }

    public void run()
    {
        Integer inputPrgChoice;
        System.out.println("Choose a program to run: ");
        System.out.println("1. int v; v=2; Print(v)");
        System.out.println("2. int a; a=2+3*5; int b; b=a+1; Print(b)");
        System.out.println("3. bool a; int v; a=true; If (a) Then {v=2} Else {v=3}; Print(v)");
        Scanner scanner = new Scanner(System.in);
        inputPrgChoice = scanner.nextInt();

        System.out.println("\n");
        
        Integer stepChoice;
        System.out.println("Choose a run method: ");
        System.out.println("1. One step");
        System.out.println("2. All steps");
        stepChoice = scanner.nextInt();

        switch (stepChoice)
        {
            case 1:
                while (true)
                {
                    PrgState crtPrgState = this.controller.getProgramStateByIndex(inputPrgChoice - 1);
                    try {
                        this.controller.oneStep(crtPrgState);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Continue? (0/1) ");
                    Integer cont = scanner.nextInt();
                    switch (cont)
                    {
                        case 0:
                            break;
                        case 1:
                            continue;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                }
                break;
            case 2:
                this.controller.allStep(inputPrgChoice - 1);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        scanner.close();
    }
}
