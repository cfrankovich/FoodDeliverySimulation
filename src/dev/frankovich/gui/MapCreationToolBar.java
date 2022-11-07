package dev.frankovich.gui;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class MapCreationToolBar 
{
    private final int WIDTH = 700;
    private final int HEIGHT = 50;
    private JPanel toolbarpanel; 
    private JButton tools[];
    /*
     *  0 : export tool
     *  1 : import tool
     *  2 : inspect tool
     *  3 : house tool
     *  4 : restaurant tool
     *  5 : road tool
     *  6 : delete tool 
     */

    public MapCreationToolBar()
    {
        toolbarpanel = new JPanel();
        tools = new JButton[7];
    }

    public void configureToolBarPanel()
    {
        toolbarpanel.setBackground(Color.RED);
        toolbarpanel.setBounds(0, 0, WIDTH, HEIGHT);
        toolbarpanel.setLayout(null);
    }

    public void configureToolBarButtons(MapCreationTool mapCreationTool)
    {
        for (int i = 0; i < tools.length; i++)
        {
            tools[i] = new JButton();
            tools[i].setBackground(Color.LIGHT_GRAY);
            tools[i].setFocusable(false);
            tools[i].setText(Integer.toString(i));
            tools[i].setBounds((100*i)-10, 0, WIDTH/tools.length, HEIGHT);
            tools[i].setBorder(new MatteBorder(0, 1, 2, 1, Color.BLACK));
            setToolBarListeners(mapCreationTool, i);
        }
    }

    private void setToolBarListeners(MapCreationTool mapCreationTool, int n)
    {
        tools[n].addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                mapCreationTool.setSelectedTool(n);
            }
        });
    }

    public void addToolBarButtons()
    {
        for (JButton btn : tools)
        {
            toolbarpanel.add(btn);
        }
    }

    public JPanel getPanel()
    {
        return toolbarpanel;
    }
    
}
