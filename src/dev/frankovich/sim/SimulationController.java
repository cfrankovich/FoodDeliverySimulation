// ******************************************************
// Class:			SimulationController
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Controlls the simulation with important methods like tick and process order
//
// Attributes:		+MenuIte: static
//					-map[][]: Node
//					-drivers: ArrayList<Driver>
//					-houses: ArrayList<House>
//					-restaurant: Restaurant
//					-minutesRan: double
//					-totalOrders: int
//					
// Methods:			+processArguments( String[] ): void
//					+generateMenu( String ): MenuItem[]
//					+getHouses( ): void
//					+getDrivers( ): void
//					+getDriverList( ): ArrayList<Driver>
//					+runSimulation( ): void
//					-tickSimulation( ): int
//					+manageOrderQueue( ): void
//					+getMap( ): Node[][]
//					+processOrders( int ): int
//					+getMinutes( ): double
//					+getRestaurant( ): Restaurant
//					+exportReport( ): void
//					
// ******************************************************

package dev.frankovich.sim;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import dev.frankovich.util.MapUtils;
import dev.frankovich.util.Report;
import dev.frankovich.util.Utils;

public class SimulationController 
{
    public static MenuItem menuItems[]; /* menuItems same for all controllers if there were to be multiple */
    private Node map[][];
    private ArrayList<Driver> drivers;
    private ArrayList<House> houses;
    private Restaurant restaurant;
    private double minutesRan;
    private int totalOrders;

    public SimulationController(String[] args)
    {
        Utils.setSettings(args);
        processArguments(args);
        if (map != null)
        {
            getHouses();
            getDrivers();
            restaurant = getRestaurant();
            minutesRan = 0;
            totalOrders = 0;
            runSimulation();
        }
    }

    public void processArguments(String[] args)
    {
        for (String arg : args)
        {
            String argSplit[] = arg.split("=");
            if (argSplit[0].equals("--create-map"))
            {
                MapUtils.createNewMap();
                map = null;
            }
            else if (argSplit[0].equals("--map"))
            {
                try
                {
                    map = MapUtils.createMap(argSplit[1]);
                } 
                catch (Exception e)
                {
                    System.out.println("[Error] Please provide a valid file.");
                    System.exit(0);
                }
            }
            else if (argSplit[0].equals("--menu"))
            {
                try
                {
                    menuItems = generateMenu(argSplit[1]);
                }
                catch (Exception e)
                {
                    System.out.println("[Error] Please provide a valid file.");
                    System.exit(0);
                }
            }

        }
    }

    public MenuItem[] generateMenu(String filePath)
    {
        try
        {
            File menuItemsFile = new File(filePath);
            Scanner input = new Scanner(menuItemsFile);
            int size = Integer.parseInt(input.nextLine());
            MenuItem[] items = new MenuItem[size];

            int k = 0;
            while (input.hasNext())
            {
                String[] iteminfo = input.nextLine().split(",");
                items[k] = new MenuItem(iteminfo);
                k++;
            }
            input.close();
            return items;
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Menu item data file contains incorrect size.");
            System.out.println("Make sure a valid size is on the first line of the file.");
            System.exit(0);
        }
        catch (Exception e)
        {
            System.out.println("Unable to read file \"" + filePath + "\"");
            System.out.println("Make sure the file inputed is a csv file in the correct format.");
            System.exit(0);
        }

        return null;
    }

    public void getHouses()
    {
        houses = new ArrayList<House>();
        for (Node[] row : map)
        {
            for (Node node : row)
            {
                if (node instanceof House) { houses.add((House)node); }
            }
        }
    }

    public void getDrivers()
    {
        drivers = new ArrayList<Driver>();
        for (int i = 0; i < Utils.numDrivers; i++) 
        {
            //drivers.add(new Driver(restaurant.getXLocation(), restaurant.getYLocation()));
            drivers.add(new Driver(houses.get(0).getXLocation(), houses.get(0).getYLocation()));
        }
    }

    public ArrayList<Driver> getDriverList()
    {
        return drivers;
    }
 
    public void runSimulation()
    {
        if (map == null) { map = MapUtils.correctMapError(); }

        System.out.println("Running Simulation....");

        /* every cycle is one minute */
        int running = 0;
        while (running == 0)
        {
            running = tickSimulation();  
            //renderSimulation();
        }

        for (Driver d : drivers)
        {
            Utils.totalGasUsage += d.getGasUsage();
        }

        System.out.println("Simulation concluded.");
        exportReport();
    }

    private int tickSimulation()
    {
        /** PROCESS ORDERS **/
        totalOrders = processOrders(totalOrders);
        minutesRan += 0.5;
        if (minutesRan >= Utils.clock.getDurationInMins())
        {
            return 2;
        }

        manageOrderQueue();

        /** COOK FOOD **/
        restaurant.cook((int)minutesRan);
        
        /** UPDATE DRIVERS **/
        for (Driver d : drivers) { d.update(this); }

        /** END CONDITION **/
        if (totalOrders > Utils.maxOrders) 
        { 
            Utils.orderFrequency = 0;
            if (restaurant.getOrderQueue().size() == 0)
                return 1;
        } 
        return 0;
    }

    public void manageOrderQueue()
    {
        if (restaurant.getOrderQueue().size() > 0)
        {
            int randomDriverIndex = (int)(Math.random() * drivers.size());
            int counter = drivers.size();
            while (drivers.get(randomDriverIndex).isDriving())
            {
                randomDriverIndex = (int)(Math.random() * drivers.size());
                counter--;
                if (counter == -1) return;
            }
            drivers.get(randomDriverIndex).updateNavigation(map, restaurant.getXLocation(), restaurant.getYLocation());
        }
    }

    public Node[][] getMap() 
    {
        return map;
    }

    public int processOrders(int orderID)
    {
        if (minutesRan % 5 == 0 && Math.random() < Utils.orderFrequency)
        {
            int randomIndex = (int)(Math.random() * houses.size()); 
            if (houses.get(randomIndex).getOrder() == null)
            {
                houses.get(randomIndex).placeOrder(orderID, restaurant, minutesRan);
                return orderID + 1;
            }
        }
        return orderID;
    }

    public double getMinutes()
    {
        return minutesRan;
    }

    public Restaurant getRestaurant()
    {
        for (Node[] row : map)
        {
            for (Node node : row)
            {
                if (node instanceof Restaurant) 
                {
                    return (Restaurant)node;
                }
            }
        }
        System.out.println("SOMETHING IS WRONG");
        return null;
    }

    public void exportReport()
    {
        Report report = new Report();    
        report.exportCSV(this, "Simulation Results.csv");
    }
   
}
