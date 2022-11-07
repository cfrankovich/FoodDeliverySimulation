// ******************************************************
// Class:			House
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			Places orders for food and keeps track of the wait time of the order.
//
// Attributes:		-waitTime: double
//					-order: Order
//					
// Methods:			+recieveFood( ): void
//					+placeOrder( MenuItem ): void
//					+toString( ): String
//					
// ******************************************************

package dev.frankovich.sim;

public class House extends Node
{
    private double waitTime;
    private Order order;
    
    public House(int x, int y)
    {
        super(x, y);
    }

    public void recieveFood()
    {

    }

    public void placeOrder(MenuItem itemsForOrder[])
    {

    }

    @Override
    public String toString()
    {
        return "3";
    }

}
