// ******************************************************
// Class:			MapUtils
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			Extra tools regarding the map of the simulation.
//
// Attributes:		
//
// Methods:			+createMap( ): void
//					+useMap( String ): Node[][]
//					+compressMap( ): void
//                  +charToNode ( char, int, int ) : Node
//					
// ******************************************************

package dev.frankovich.util;

import dev.frankovich.sim.House;
import dev.frankovich.sim.Node;
import dev.frankovich.sim.Restaurant;
import dev.frankovich.sim.Road;

import java.io.File;
import java.util.Scanner;

import dev.frankovich.gui.MapCreationTool;

public class MapUtils
{
    public static void createMap()
    {
        new MapCreationTool();
    }

    public static Node[][] useMap(String fileName)
    {
        Node map[][] = new Node[20][20];
        int i, k;
        i = 0;
        try
        {
            File mapFile = new File(fileName);
            Scanner input = new Scanner(mapFile);
            while (input.hasNext())
            {
                String line = input.nextLine();
                k = 0;
                for (char c : line.toCharArray())
                {
                    map[i][k] = charToNode(c, i, k);
                    k++;
                }
                i++;
            }
            input.close();
        }
        catch (Exception e)
        {
            System.out.println("Unable to read file \"" + fileName + "\"");
            System.out.println("Make sure the file inputed is a text file with numbers.");
            System.exit(0);
        }
        return map;
    } 

    public static Node charToNode(char c, int i, int k)
    {
        if (c == '0') { return new Node(i, k); }
        else if (c == '3') { return new House(i, k); }
        else if (c == '4') { return new Restaurant(i, k); }
        else if (c == '5') { return new Road(i, k); }
        return null; 
    }

    public void compressMap()
    {

    }

    public static Node[][] correctMapError()
    {
        System.out.println("No map file given.");
        System.out.print("Would you like to input a map file? [Y/n]\n> ");
        Scanner in = new Scanner(System.in);

        String yesOrNo = in.nextLine().toLowerCase(); 
        if (yesOrNo.equals("") || yesOrNo.equals("y"))
        {
            System.out.print("Map File Path: ");
            String fileName = in.nextLine();
            in.close();
            return useMap(fileName);
        }
        else
        {
            in.close();
            System.out.println("Exiting program...");
            System.exit(1);
        }
        return null;
    }


}