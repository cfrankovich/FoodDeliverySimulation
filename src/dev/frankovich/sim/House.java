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
