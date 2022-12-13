// ******************************************************
// Class:			MenuItem
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Items to be ordered
//
// Attributes:		-prepTime: double
//					-itemName: String
//					-price: double
//					-frequency: double
//					-itemType: int
//					
// Methods:			-updateAttributes( String[] ): void
//					+getPrepTime( ): double
//					+getItemType( ): int
//					+getName( ): String
//					+getPrice( ): double
//					
// ******************************************************

package dev.frankovich.sim;

public class MenuItem
{
    private double prepTime;
    private String itemName;
    private double price;
    private double frequency;
    private int itemType; /* 1 - drink, 2 - entree, 3 - side */

    public MenuItem(String[] iteminfo)
    {
        updateAttributes(iteminfo);
    }

    private void updateAttributes(String[] iteminfo)
    {
        itemName = iteminfo[0];
        prepTime = Double.parseDouble(iteminfo[1]);
        price = Double.parseDouble(iteminfo[2]);
        frequency = Double.parseDouble(iteminfo[3]);
        itemType = Integer.parseInt(iteminfo[4]);
    }

    public double getPrepTime() { return prepTime; }
    
    public int getItemType() { return itemType; }

    public String getName() { return itemName; }

    public double getPrice() { return price; }

}