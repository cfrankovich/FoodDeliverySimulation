// ******************************************************
// Class:			MapCreationInspector
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Inspector element for the map creation tool
//
// Attributes:		-inspectorpanel: JPanel
//					
// Methods:			+configureInspectorPanel( ): void
//					+void( MapCreationGridNode ): static
//					+getPanel( ): JPanel
//					
// ******************************************************

package dev.frankovich.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class MapCreationInspector 
{
    private final int WIDTH = 185;
    private final int HEIGHT = 500;
    private JPanel inspectorpanel;

    public MapCreationInspector()
    {
        inspectorpanel = new JPanel();
    }

    public void configureInspectorPanel()
    {
        inspectorpanel.setBackground(Color.LIGHT_GRAY);
        inspectorpanel.setBorder(new MatteBorder(1, 1, 1, 3, Color.BLACK));
        inspectorpanel.setBounds(0, 50, WIDTH, HEIGHT);
        JLabel titleLabel = new JLabel("Inspector");
        titleLabel.setBounds(10, 10, 100, 100);
        titleLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        titleLabel.setForeground(Color.BLACK);
        inspectorpanel.add(titleLabel);
    }

    public static void updateInspector(MapCreationGridNode node)
    {

    }

    public JPanel getPanel()
    {
        return inspectorpanel;
    }
    
}
