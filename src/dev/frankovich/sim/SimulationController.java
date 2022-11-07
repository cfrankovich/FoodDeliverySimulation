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
//					+printMap( ): void
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

    public SimulationController()
    {

    }

    public void processArguments(SimulationController controller, String[] args)
    {
        for (String arg : args)
        {
            String argSplit[] = arg.split("=");
            if (argSplit[0].equals("--create-map"))
            {
                MapUtils mapUtils = new MapUtils();
                mapUtils.createMap();
            }
            else if (argSplit[0].equals("--map"))
            {
                try
                {
                    MapUtils mapUtils = new MapUtils();
                    map = mapUtils.useMap(argSplit[1]);
                } 
                catch (Exception e)
                {
                    System.out.println("Please provide a valid file.");
                    System.exit(0);
                }
            }

        }
    }

    public void runSimulation()
    {

    }

    public void exportReport()
    {

    }
    
    public void printMap()
    {
        for (Node[] row : map)
        {
            for (Node node : row)
            {
                System.out.print(node);
            }
            System.out.print("\n");
        }
    }

}
