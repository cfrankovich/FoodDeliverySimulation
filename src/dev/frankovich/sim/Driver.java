// ******************************************************
// Class:			Driver
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Picks up and delivers food
//
// Attributes:		-xLocation: int
//					-yLocation: int
//					-ordersPickedUp: ArrayList<Order>
//					-gasPrice: double
//					-distanceTraveled: int
//					-isDriving: boolean
//					-path: ArrayList<ArrayList<Integer>>
//					
// Methods:			+update( SimulationController ): void
//					+getGasUsage( ): int
//					+pickUpFood( SimulationController, Restaurant ): void
//					+deliverFood( SimulationController, House ): void
//					+addDistance( double ): void
//					+hasOrder( ): boolean
//					-calculatePath( Node, int, int ): void
//					-wasPrevious( Node, ArrayList<ArrayList<Integer>> ): boolean
//					-clonePath( ArrayList<ArrayList<Integer>> ): ArrayList<ArrayList<Integer>>
//					-getNeighboringNodes( Node, int, int ): Node[][]
//					+updateNavigation( Node, int, int ): void
//					+isDriving( ): boolean
//					
// ******************************************************

package dev.frankovich.sim;

import java.util.ArrayList;
import java.util.Arrays;

public class Driver 
{
    private int xLocation;
    private int yLocation;
    private ArrayList<Order> ordersPickedUp;
    private double gasPrice;
    private int distanceTraveled;
    private boolean isDriving;
    private ArrayList<ArrayList<Integer>> path; 
    private final int driverOrderHoldPreference = 2;

    public Driver(int x, int y)
    {
        xLocation = x;
        yLocation = y;
        ordersPickedUp = new ArrayList<Order>();
        distanceTraveled = 0;
        isDriving = false;
        path = null; 
    }

    public void update(SimulationController controller)
    {
        /* drive  */
        try
        {
            xLocation = path.get(0).get(0);
            yLocation = path.get(0).get(1);
            path.remove(0);
            distanceTraveled += 1;
        }
        catch (Exception e)
        {
            if (controller.getMap()[xLocation][yLocation] instanceof Restaurant)
            {
                pickUpFood(controller, controller.getRestaurant());
            }
            else if (controller.getMap()[xLocation][yLocation] instanceof House && ordersPickedUp.size() > 0)
            {
                deliverFood(controller, ordersPickedUp.get(0).getHouseOrdered());
            }
        }
    }

    public int getGasUsage()
    {
        return distanceTraveled;
    } 

    public void pickUpFood(SimulationController controller, Restaurant restaurant)
    {
        int count = 0;

        Order newOrder = restaurant.getNextOrder();
        if (newOrder == null) { return; }
        while (newOrder != null && count < driverOrderHoldPreference)
        {
            newOrder.setPickedUpTime((int)controller.getMinutes());
            ordersPickedUp.add(newOrder);
            newOrder = restaurant.getNextOrder();
            count++;
        }
        updateNavigation(controller.getMap(), ordersPickedUp.get(0).getHouseOrdered().getXLocation(), ordersPickedUp.get(0).getHouseOrdered().getYLocation());
        isDriving = true;
    }

    public void deliverFood(SimulationController controller, House house)
    {
        ordersPickedUp.remove(0);
        house.recieveFood(controller.getMinutes());
        if (ordersPickedUp.size() < 0)
        {
            updateNavigation(controller.getMap(), controller.getRestaurant().getXLocation(), controller.getRestaurant().getYLocation());
            isDriving = true;
            return;
        }
        isDriving = false;
    }

    public void addDistance(double distance)
    {
        distanceTraveled += distance;
    }

    public boolean hasOrder()
    {
        return ordersPickedUp.size() > 0;
    }

    /* big mess but works */
    private void calculatePath(Node map[][], int x, int y)
    {
        ArrayList<ArrayList<ArrayList<Integer>>> possiblePaths = new ArrayList<ArrayList<ArrayList<Integer>>>(); 
        ArrayList<ArrayList<Integer>> initialPath = new ArrayList<ArrayList<Integer>>(Arrays.asList(new ArrayList<Integer>(Arrays.asList(xLocation, yLocation))));
        possiblePaths.add(initialPath);

        boolean foundPath = false;
        while (!foundPath)
        {
            for (int i = 0; i < possiblePaths.size(); i++)
            {
                path = possiblePaths.get(i);
                int lastX = path.get(path.size()-1).get(0);
                int lastY = path.get(path.size()-1).get(1);

                Node[][] neighbors = getNeighboringNodes(map, lastX, lastY); 
                int nums[][] = new int[][]{ {0, 1, 1, 2}, {1, 0, 2, 1} };
                boolean roadFound = false;
                for (int k = 0; k < 4; k++)
                { 
                    Node node = neighbors[nums[0][k]][nums[1][k]]; 
                    if (node == null || wasPrevious(node, path)) { continue; }

                    if (node instanceof Road)
                    {
                        if (!roadFound)
                        {
                            path.add(new ArrayList<Integer>(Arrays.asList(lastX + (nums[0][k]-1), lastY + (nums[1][k]-1))));
                            possiblePaths.remove(i);
                            possiblePaths.add(path);
                            roadFound = true;
                        }
                        else
                        {
                            /* copy the path and add the new to the 3d array */
                            ArrayList<ArrayList<Integer>> newPath = clonePath(path);
                            newPath.add(new ArrayList<Integer>(Arrays.asList(lastX + (nums[0][k]-1), lastY + (nums[1][k]-1))));
                            possiblePaths.add(newPath);
                        }
                    } 
                    else if (node.getXLocation() == x && node.getYLocation() == y)
                    {
                        /* return the path */ 
                        foundPath = true;
                        path.add(new ArrayList<Integer>(Arrays.asList(lastX + (nums[0][k]-1), lastY + (nums[1][k]-1))));
                        possiblePaths.remove(i);
                        possiblePaths.add(path);
                        return;
                    }
                }
            }
            
        }
    }

    private boolean wasPrevious(Node n, ArrayList<ArrayList<Integer>> p)
    {
        int x = n.getXLocation();
        int y = n.getYLocation();

        for (ArrayList<Integer> coords : p)
        {
            if (coords.get(0) == x && coords.get(1) == y)
            {
                return true;
            }
        }
        return false;
    }

    private ArrayList<ArrayList<Integer>> clonePath(ArrayList<ArrayList<Integer>> path)
    {
        ArrayList<ArrayList<Integer>> clonedPath = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> coords : path)
        {
            clonedPath.add(coords);
        }
        return clonedPath;
    }

    private Node[][] getNeighboringNodes(Node map[][], int x, int y)
    {
        Node[][] neighbors = new Node[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                try
                {
                    neighbors[i][k] = map[x + i - 1][y + k - 1]; 
                }
                catch (Exception e)
                {
                    neighbors[i][k] = null;
                }
            }
        }
        return neighbors;
    } 

    public void updateNavigation(Node map[][], int x, int y)
    {
        isDriving = true;
        calculatePath(map, x, y);
    }

    public boolean isDriving()
    {
        return isDriving;
    }
    
}
