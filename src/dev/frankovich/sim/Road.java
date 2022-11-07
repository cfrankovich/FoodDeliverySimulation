// ******************************************************
// Class:			Road
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			The "drivable" path for the drivers. Contains traffic percentage and speed limit for accurate results. Extends Node class.
//
// Attributes:		-trafficPercentage: double
//					-speedLimit: double
//					
// Methods:			+toString( ): String
//					
// ******************************************************

package dev.frankovich.sim;

public class Road extends Node 
{
    private double trafficPercentage;    
    private double speedLimit;

    public Road(int x, int y)
    {
        super(x, y);
    }

    @Override
    public String toString()
    {
        return "5";
    }


}
