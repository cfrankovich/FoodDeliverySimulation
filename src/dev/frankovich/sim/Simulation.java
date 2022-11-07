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
