// ******************************************************
// Class:			Road
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Drivable or "walkable" routes for drivers
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
