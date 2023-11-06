package com.assignment2.robi.view;
import java.util.Scanner;
import com.assignment2.robi.models.ADTs.IMap;
import com.assignment2.robi.models.ADTs.MyMap;

public class TextMenu 
{
    private IMap<String, Command> commands;

    public TextMenu()
    {
        commands = new MyMap<String, Command>();
    }

    public void addCommand(Command c)
    {
        commands.add(c.getKey(), c);
    }

    private void printMenu()
    {
        for (Command c : commands.values())
        {
            String line = String.format("%4s: %s", c.getKey(), c.getDescription());
            System.out.println(line);
        }
    }

    public void show()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if (com == null)
            {
                System.out.println("Invalid option");
                continue;
            }
            com.execute();
        }
    }
}
