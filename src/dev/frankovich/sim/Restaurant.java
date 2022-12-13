// ******************************************************
// Class:			Restaurant
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Prepares food and serves as the hub
//
// Attributes:		-orderQueue: ArrayList<Order>
//					
// Methods:			+toString( ): String
//					+getOrderQueue( ): ArrayList<Order>
//					+addOrder( Order ): void
//					+cook( int ): void
//					+getXLocation( ): int
//					+getYLocation( ): int
//					+getNextOrder( ): Order
//					
// ******************************************************

package dev.frankovich.sim;

import java.util.ArrayList;

public class Restaurant extends Node 
{
    private ArrayList<Order> orderQueue;
    
    public Restaurant(int x, int y)
    {
        super(x, y);
        orderQueue = new ArrayList<Order>();
    }

    public String toString()
    {
        return "4";
    }

    public ArrayList<Order> getOrderQueue() { return orderQueue; } 

    public void addOrder(Order order)
    {
        orderQueue.add(order);
    }

    public void cook(int mins)
    {
        for (Order order : orderQueue) 
        {
            order.cook(mins);
        }
    }

    public int getXLocation() { return xLocation; }

    public int getYLocation() { return yLocation; }

    public Order getNextOrder()
    {
        if (orderQueue.size() == 0) return null;

        if (orderQueue.get(0).isReady())
        {
            return orderQueue.remove(0);
        }
        else
        {
            return null;
        }
    }
}
