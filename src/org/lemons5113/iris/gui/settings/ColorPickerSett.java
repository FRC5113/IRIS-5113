package org.lemons5113.iris.gui.settings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ColorPickerSett extends SettingsBase
{

	public enum type
	{
		HLS, HSV, BGR
	};

	public static Color colorHigh;
	public static Color colorLow;
	public static JColorChooser chooserHigh;
	public static JColorChooser chooserLow;
	public JComboBox typeBox;

	public void init()
	{
		typeBox = new JComboBox(type.values());
		add(typeBox);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		colorHigh = new Color(1, 1, 1);
		colorLow = new Color(1, 1, 1);
		chooserHigh = new JColorChooser(colorHigh);
		chooserLow = new JColorChooser(colorLow);
		add(new JLabel("Lower Bounds"));
		add(chooserLow);
		add(new JLabel("Upper Bounds"));
		add(chooserHigh);

		setBorder(BorderFactory.createTitledBorder("Colors"));

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
