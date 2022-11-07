// ******************************************************
// Class:			Node
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			The basic or empty part of the map.
//
// Attributes:		-xLocation: int
//					-yLocation: int
//					
// Methods:			+toString( ): String
//					
// ******************************************************

package dev.frankovich.sim;

public class Node 
{
    private int xLocation;
    private int yLocation;    

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
}
