// ******************************************************
// Class:			Restaurant
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			Recieves the orders from houses and assigns drivers to the different orders. Extends the Node class.
//
// Attributes:		-orderQueue[]: Order
//					
// Methods:			+assignDriver( ): void
//					+toString( ): String
//					
// ******************************************************

package dev.frankovich.sim;

public class Restaurant extends Node 
{
    private Order orderQueue[];
    
    public Restaurant(int x, int y)
    {
        super(x, y);
    }

    public void assignDriver()
    {

    }

    public String toString()
    {
        return "4";
    }
}
