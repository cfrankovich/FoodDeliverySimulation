package dev.frankovich.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.*;

public class MapCreationGridNode 
{
    private final int WIDTH = 25;
    private final int HEIGHT = 25;
    private JButton button;
    private int nodeType;
    private int xLocation, yLocation;
    /* 
     * 0 : nothing
     * 3 : house
     * 4 : restaurant
     * 5 : road
     */

    public MapCreationGridNode(JPanel panel, MapCreationTool mapCreationTool, int x, int y)
    {
        nodeType = 0;
        button = new JButton();
        configureButton(mapCreationTool, x, y);
        panel.add(button);
        xLocation = x;
        yLocation = y;
    }

    private void configureButton(MapCreationTool mapCreationTool, int x, int y)
    {
        button.setBounds((x*25)+184, (y*25)+50, WIDTH, HEIGHT);
        button.setBackground(Color.WHITE);
        button.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

        button.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent e)
            {
                actUponTool(mapCreationTool.getSelectedTool());
            }
            public void mouseEntered(MouseEvent e)
            {
                if (nodeType == 0)
                    button.setBackground(Color.LIGHT_GRAY);
            }  
            public void mouseExited(MouseEvent e)
            {
                if (nodeType == 0)
                    button.setBackground(Color.WHITE);
            }
        });
    }

    private void actUponTool(int selectedTool)
    {
        System.out.println(xLocation + " " + yLocation);
        switch(selectedTool)
        {
            case 2:
                /* inspect tool */
                break;
            
            case 3:
                /* house tool */
                button.setBackground(Color.BLUE);
                nodeType = 3;                
                break;

            case 4:
                /* restaurant tool */
                button.setBackground(Color.RED);
                nodeType = 4;
                break;

            case 5:
                /* road tool */
                button.setBackground(Color.BLACK);
                nodeType = 5;
                break;

            case 6:
                /* delete tool */
                button.setBackground(Color.WHITE);
                nodeType = 0;
                break;
        }
    }

    public int getNodeType()
    {
        return nodeType;
    }

}
