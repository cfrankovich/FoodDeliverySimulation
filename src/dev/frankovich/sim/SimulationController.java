// ******************************************************
// Class:			SimulationController
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			Controlls the simulation. This class is responsible for all "high level" behavior in the simulation.
//
// Attributes:		-map[][]: Node
//					-duration: double
//					-menuItems[]: MenuItem
//					-drivers[]: Driver
//					
// Methods:			+processArguments( SimulationController, String[] ): void
//					+runSimulation( ): void
//					+exportReport( ): void
//					
// ******************************************************

package dev.frankovich.sim;

import java.io.File;
import java.util.Map;

import dev.frankovich.util.MapUtils;

public class SimulationController 
{
    private Node map[][];
    private double duration;
    private MenuItem menuItems[];
    private Driver drivers[];
    private int minutesRan;

    public SimulationController(String[] args)
    {
        processArguments(args);
        minutesRan = 0;
    }

    public void processArguments(String[] args)
    {
        for (String arg : args)
        {
            String argSplit[] = arg.split("=");
            if (argSplit[0].equals("--create-map"))
            {
                MapUtils.createMap();
            }
            else if (argSplit[0].equals("--map"))
            {
                try
                {
                    map = MapUtils.useMap(argSplit[1]);
                } 
                catch (Exception e)
                {
                    System.out.println("[Error] Please provide a valid file.");
                    System.exit(0);
                }
            }

        }
    }

    public void runSimulation()
    {
        if (map == null) { map = MapUtils.correctMapError(); }

        /* every cycle is one minute */
        boolean running = true;
        while (running)
        {
            minutesRan++;
        }

    }

    public void exportReport()
    {

    }
    
}
