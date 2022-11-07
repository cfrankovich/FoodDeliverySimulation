// ******************************************************
// Class:			MapCreationTool
// Name:			Carson Frankovich
// Date:			2022-11-07
//
// Purpose:			Runs a graphical environment for the user to create and save a map.
//
// Attributes:		-frame: JFrame
//					-panel: JPanel
//					-selectedTool: int
//					-map[][]: MapCreationGridNode
//					
// Methods:			-configureJFrame( ): void
//					-configureJPanel( ): void
//					-createGrid( ): void
//					+setSelectedTool( int ): void
//					+getSelectedTool( ): int
//					-exportMap( ): void
//					
// ******************************************************

package dev.frankovich.gui;

import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapCreationTool 
{
    private JFrame frame;
    private JPanel panel; 
    private final int FRAME_WIDTH = 700;
    private final int FRAME_HEIGHT = 589;
    private int selectedTool;
    private MapCreationGridNode map[][];

    public MapCreationTool()
    {
        map = new MapCreationGridNode[20][20];
        selectedTool = 0;
        frame = new JFrame("FDS | Map Creation Tool");
        panel = new JPanel();
        configureJPanel();
        configureJFrame();
    }

    private void configureJFrame()
    {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
    }

    private void configureJPanel()
    {
        panel.setLayout(null);
        panel.setBackground(Color.GREEN);
        createGrid();

        MapCreationToolBar toolbar = new MapCreationToolBar();
        toolbar.configureToolBarPanel();
        toolbar.configureToolBarButtons(this);
        toolbar.addToolBarButtons();
        panel.add(toolbar.getPanel());

        MapCreationInspector inspector = new MapCreationInspector();
        inspector.configureInspectorPanel();
        panel.add(inspector.getPanel());

    }

    private void createGrid()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int k = 0; k < map[i].length; k++)
            {
                map[k][i] = new MapCreationGridNode(panel, this, i, k);
            }
        }
    }

    public void setSelectedTool(int toolIndex)
    {
        selectedTool = toolIndex;
        if (selectedTool == 0)
        {
            exportMap();
        }
    }

    public int getSelectedTool()
    {
        return selectedTool;
    }

    private void exportMap()
    {
        try
        {
            File exportFile = new File("newmap.txt");
            PrintWriter output = new PrintWriter(exportFile);
            for (MapCreationGridNode[] row : map)
            {
                String line = "";
                for (MapCreationGridNode node : row)
                {
                    line += Integer.toString(node.getNodeType());
                }
                output.print(line + "\n");
            }
            output.close();
        } 
        catch(Exception e)
        {
            System.out.println("Export Failed!");
        }
    }

}

