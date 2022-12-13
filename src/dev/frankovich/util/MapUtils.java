// ******************************************************
// Class:			MapUtils
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Utilities to aid in working with the map
//
// Attributes:		
// Methods:			+void( ): static
//					+Node[][]( String ): static
//					+Node( char, int, int ): static
//					+void( String ): static
//					+void( String ): static
//					+Node[][]( ): static
//					
// ******************************************************

package dev.frankovich.util;

import dev.frankovich.sim.House;
import dev.frankovich.sim.Node;
import dev.frankovich.sim.Restaurant;
import dev.frankovich.sim.Road;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import dev.frankovich.gui.MapCreationTool;

public class MapUtils
{
    public static void createNewMap()
    {
        new MapCreationTool("newmap.txt");
    }

    public static Node[][] createMap(String fileName)
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
                    if (c == '-')
                    {
                        decompressMap(fileName);
                        input.close();
                        return createMap(fileName);
                    }
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

    public static void compressMap(String fileName)
    {
        String output = "";
        try
        {
            File mapFile = new File(fileName);
            Scanner input = new Scanner(mapFile);

            while (input.hasNext())
            {
                String line = input.nextLine();
                char lineChars[] = line.toCharArray();
                char current = lineChars[0]; 
                int count = 1;
                for (int i = 1; i < line.length(); i++)
                {
                    if (lineChars[i] == current)
                    {
                        count++;
                    }
                    else
                    {
                        output += "" + current;
                        output += "" + count + "-";
                        current = lineChars[i]; 
                        count = 1;
                    }
                }
                output += "" + current + "" + count + "-\n";
            }
            input.close();
            PrintWriter pWriter = new PrintWriter(new FileOutputStream(fileName, false));
            pWriter.print(output);
            pWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to read file \"" + fileName + "\"");
            System.out.println("Make sure the file inputed is a text file with numbers.");
            System.out.println("Continuing without encoding map file...");
        }
    }

    public static void decompressMap(String fileName)
    {
        String output = "";
        try
        {
            File mapFile = new File(fileName);
            Scanner input = new Scanner(mapFile);

            while (input.hasNext())
            {
                String line = input.nextLine();
                String[] splitLine = line.split("-");
                for (String s : splitLine)
                {
                    char current = s.charAt(0);
                    int count = Integer.parseInt(s.substring(1));
                    for (int i = 0; i < count; i++)
                    {
                        output += "" + current; 
                    }
                }
                output += "\n";
            }
            input.close();
            PrintWriter pWriter = new PrintWriter(new FileOutputStream(fileName, false));
            pWriter.print(output);
            pWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to read file \"" + fileName + "\"");
            System.out.println("Make sure the file inputed is a text file with numbers.");
            System.out.println("Continuing without encoding map file...");
        }
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
            return createMap(fileName);
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