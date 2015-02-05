package org.lemons5113.iris.gui.settings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class IRISColorPicker extends JPanel 
{
	
	public static Color colorHigh;
	public static Color colorLow;
	private JColorChooser chooserHigh;
	private JColorChooser chooserLow;

	
	public void init()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		colorHigh = new Color(1, 1, 1);
		colorLow = new Color(1, 1, 1);
		chooserHigh = new JColorChooser(colorHigh);
		chooserLow = new JColorChooser(colorLow);
		add(chooserHigh);
		add(chooserLow);
		
		setBorder(BorderFactory.createTitledBorder("Color"));

	}
	
	@Override
    protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
    }
	
    public void update()
    {    	
    	repaint();
    	
    	colorHigh = chooserHigh.getColor();
    	colorLow = chooserLow.getColor();
    	
    }
}
