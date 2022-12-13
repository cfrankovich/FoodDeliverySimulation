// ******************************************************
// Class:			Utils
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Utilities for general purpose behavior
//
// Attributes:		+doubl: static
//					+doubl: static
//					+doubl: static
//					+doubl: static
//					+in: static
//					+in: static
//					-Strin: static
//					
// Methods:			+ArrayList<Order>( ): static
//					+Clock( ): static
//					+MenuItem[]( ): static
//					-String[]( String ): static
//					+void( String[] ): static
//					
// ******************************************************

package dev.frankovich.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import dev.frankovich.sim.MenuItem;
import dev.frankovich.sim.Order;
import dev.frankovich.sim.SimulationController;

public class Utils 
{
    public static double orderFrequency;
    public static double totalGasUsage;
    public static double USDperGallonGas;
    public static ArrayList<Order> archivedOrders = new ArrayList<Order>();
    public static double driverSalary;
    public static int numDrivers;
    public static Clock clock = new Clock();
    public static int maxOrders = Integer.MAX_VALUE;
    private static String defaultArgs = "--use-settings=DEFAULTS\n--order-frequency=0.8\n--price-per-gallon=3.2\n--driver-salary=10\n--num-drivers=1\n--start-time=8\n--end-time=23"; 

    public static MenuItem[] generateOrder()
    {
        /* just a fast way to put it for now... */
        MenuItem[] items = new MenuItem[3];
        items[0] = SimulationController.menuItems[0];
        items[1] = SimulationController.menuItems[1];
        items[2] = SimulationController.menuItems[2];
        return items;
    }

    private static String[] useSettingsFile(String fileName)
    {
        try
        {
            String args = "";
            File settingsFile = new File(fileName);
            Scanner input = new Scanner(settingsFile);
            while (input.hasNext())
            {
                args += "--" + input.nextLine() + "\n";
            }
            input.close();
            return args.split("\n");
        }
        catch(Exception e)
        {
            System.out.println("Error. Could not read settings file.");
            System.out.println("Using Default Settings: ");
            System.out.println(defaultArgs);
            return defaultArgs.split("\n");
        }
    }

    public static void setSettings(String[] args)
    {
        boolean settingsFileUsed = false;
        for (String arg : args)
        {
            String argSplit[] = arg.split("=");
            if (argSplit[0].equals("--use-settings"))
            {
                if (argSplit[1].equals("DEFAULTS")) { settingsFileUsed = true; continue; }
                setSettings(useSettingsFile(argSplit[1]));
                settingsFileUsed = true;
            }
            else if (argSplit[0].equals("--order-frequency"))
            {
                orderFrequency = Double.parseDouble(argSplit[1]);
            }
            else if (argSplit[0].equals("--price-per-gallon"))
            {
                USDperGallonGas = Double.parseDouble(argSplit[1]);
            }
            else if (argSplit[0].equals("--driver-salary"))
            {
                driverSalary = Double.parseDouble(argSplit[1]);
            }
            else if (argSplit[0].equals("--num-drivers"))
            {
                numDrivers = Integer.parseInt(argSplit[1]);
            }
            else if (argSplit[0].equals("--start-time"))
            {
                clock.setStartTime(Integer.parseInt(argSplit[1]));
            }
            else if (argSplit[0].equals("--end-time"))
            {
                clock.setEndTime(Integer.parseInt(argSplit[1]));
            }
            else if (argSplit[0].equals("--max-orders"))
            {
                maxOrders = Integer.parseInt(argSplit[1]);
            }
        }
        clock.calculateDuration();
        if (!settingsFileUsed)
        {
            setSettings(defaultArgs.split("\n"));
        }
    }

}
