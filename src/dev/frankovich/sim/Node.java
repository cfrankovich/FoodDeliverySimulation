// ******************************************************
// Class:			Node
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Basic blueprint for any node in the map
//
// Attributes:		
// Methods:			+toString( ): String
//					+getXLocation( ): int
//					+getYLocation( ): int
//					
// ******************************************************

package dev.frankovich.sim;

public class Node 
{
    protected int xLocation;
    protected int yLocation;    

    public Node(int x, int y)
    {
        xLocation = x;
        yLocation = y; 
    }

    @Override
    public String toString()
    {
        return "0";
    }

    public int getXLocation()
    {
        return xLocation;
    }

    public int getYLocation()
    {
        return yLocation;
    }

}
