// ******************************************************
// Class:			House
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Places orders
//
// Attributes:		-waitTime: double
//					-order: Order
//					
// Methods:			+recieveFood( double ): void
//					+placeOrder( int, Restaurant, double ): void
//					+getOrder( ): Order
//					+toString( ): String
//					+update( ): void
//					
// ******************************************************

package dev.frankovich.sim;

import dev.frankovich.util.Utils;

public class House extends Node
{
    private double waitTime;
    private Order order;
    
    public House(int x, int y)
    {
        super(x, y);
    }

    public void recieveFood(double d)
    {
        order.setDeliveredTime((int)d);
        waitTime = d - waitTime;
        Utils.archivedOrders.add(order);
        order = null;
    }

    public void placeOrder(int orderID, Restaurant restaurant, double minutesRan)
    {
        waitTime = minutesRan; 
        order = new Order(orderID, this, (int)minutesRan);
        MenuItem items[] = Utils.generateOrder();
        for (MenuItem item : items)
        {
            order.addItem(item);
        }
        order.calculatePrepTime();
        restaurant.addOrder(order);
    }

    public Order getOrder()
    {
        return order;
    }

    @Override
    public String toString()
    {
        return "3";
    }

    public void update()
    {
        waitTime++;
    }

}
