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
    public void createMap()
    {
        new MapCreationTool();
    }

    public Node[][] useMap(String fileName)
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
                    if (c == '0') { map[i][k] = new Node(i, k); }
                    else if (c == '3') { map[i][k] = new House(i, k); }
                    else if (c == '4') { map[i][k] = new Restaurant(i, k); }
                    else if (c == '5') { map[i][k] = new Road(i, k); }
                    k++;
                }
                i++;
            }
            input.close();
        }
        catch (Exception e)
        {
            System.out.println("Unable to read file \"" + fileName + "\"");
            System.exit(0);
        }
        return map;
    } 

    public void compressMap()
    {

    }

}