// ******************************************************
// Class:			Simulation
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			Runs the program and processes arguments.
//
// Attributes:		
//
// Methods:			+void( String[] ): static
//					
// ******************************************************

package dev.frankovich.sim;

public class Simulation 
{
    public static void main(String[] args)
    {
        SimulationController controller = new SimulationController();
        controller.processArguments(controller, args);
        controller.runSimulation();
        //controller.printMap();
    }

}
