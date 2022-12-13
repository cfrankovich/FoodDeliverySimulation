// ******************************************************
// Class:			Order
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Contains information about an order of food
//
// Attributes:		-prepTime: double
//					-items: ArrayList<MenuItem>
//					-orderID: int
//					-houseOrdered: House
//					-price: double
//					-timeOrdered: int
//					-timeReady: int
//					-timePickedUp: int
//					-timeDelivered: int
//					-milesTravelled: int
//					
// Methods:			+setPickedUpTime( int ): void
//					+setDeliveredTime( int ): void
//					+milesTravelled( ): int
//					+calculatePrepTime( ): double
//					+getTotalTime( ): int
//					+getTimeDelivered( ): int
//					+getTimePickedUp( ): int
//					+getTimeOrdered( ): int
//					+getTimeReady( ): int
//					+getPrice( ): double
//					+getItems( ): ArrayList<MenuItem>
//					+addItem( MenuItem ): void
//					+getHouseOrdered( ): House
//					+getID( ): int
//					+isReady( ): boolean
//					+cook( int ): void
//					
// ******************************************************

package dev.frankovich.sim;

import java.util.ArrayList;

public class Order 
{
    private double prepTime;
    private ArrayList<MenuItem> items;
    private int orderID;
    private House houseOrdered;
    private double price;

    private int timeOrdered;
    private int timeReady;
    private int timePickedUp;
    private int timeDelivered;

    private int milesTravelled;
    
    public Order(int id, House house, int timeOrdered)
    {
        orderID = id;
        houseOrdered = house;
        items = new ArrayList<MenuItem>();
        this.timeOrdered = timeOrdered;
    }

    public void setPickedUpTime(int t)
    {
        timePickedUp = t;
    }

    public void setDeliveredTime(int t)
    {
        timeDelivered = t;
    }

    public int milesTravelled()
    {
        return milesTravelled;
    }

    public double calculatePrepTime()
    {
        double total = 0.0;
        double total2 = 0.0;
        for (MenuItem item : items)
        {
            total += item.getPrepTime();
            total2 += item.getPrice();
        }
        prepTime = total;
        price = total2;
        return total;
    }

    public int getTotalTime()
    {
        return timeDelivered - timeOrdered;
    }

    public int getTimeDelivered()
    {
        return timeDelivered;
    }

    public int getTimePickedUp()
    {
        return timePickedUp;
    }

    public int getTimeOrdered()
    {
        return timeOrdered;
    }


    public int getTimeReady()
    {
        return timeReady;
    }

    public double getPrice()
    {
        return price;
    }

    public ArrayList<MenuItem> getItems()
    {
        return items;
    }

    public void addItem(MenuItem item)
    {
        items.add(item);
    }

    public House getHouseOrdered()
    {
        return houseOrdered;
    }

    public int getID()
    {
        return orderID;
    }

    public boolean isReady()
    {
        return prepTime <= 0;
    }

    public void cook(int mins)
    {
        if (prepTime <= 0)
        {
            timeReady = mins;
        }
        else
        {
            prepTime -= 0.5;
        }
    } 
}
