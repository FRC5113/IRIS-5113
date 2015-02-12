package org.lemons5113.iris.gui.settings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorPickerSett extends SettingsBase
{

	public enum ColorSpace
	{
		HLS, HSV, BGR
	};

	public enum ColorPreset
	{
		CUSTOM, YELLOWTOTE_HLS, GRAYTOTE_HLS, GREENBIN_HLS, GREENNOODLE_HLS
	};

	private JSlider slider1;
	private JSlider slider2;
	private JSlider slider3;
	private JSlider slider4;
	private JSlider slider5;
	private JSlider slider6;

	private JSpinner spinner1;
	private JSpinner spinner2;
	private JSpinner spinner3;
	private JSpinner spinner4;
	private JSpinner spinner5;
	private JSpinner spinner6;

	public JComboBox typeBox;

	public JComboBox presetBox;

	private int value1;
	private int value2;
	private int value3;
	private int value4;
	private int value5;
	private int value6;

	private void initSliders()
	{

		slider1 = new JSlider(JSlider.HORIZONTAL, 0, 255, 120);
		slider1.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				value1 = (int) source.getValue();
				spinner1.setValue(value1);
			}
		});

		slider2 = new JSlider(JSlider.HORIZONTAL, 0, 255, 120);
		slider2.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				value2 = (int) source.getValue();
				spinner2.setValue(value2);
			}
		});

		slider3 = new JSlider(JSlider.HORIZONTAL, 0, 255, 120);
		slider3.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				value3 = (int) source.getValue();
				spinner3.setValue(value3);
			}
		});

		slider4 = new JSlider(JSlider.HORIZONTAL, 0, 255, 120);
		slider4.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				value4 = (int) source.getValue();
				spinner4.setValue(value4);
			}
		});

		slider5 = new JSlider(JSlider.HORIZONTAL, 0, 255, 120);
		slider5.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				value5 = (int) source.getValue();
				spinner5.setValue(value5);
			}
		});

		slider6 = new JSlider(JSlider.HORIZONTAL, 0, 255, 120);
		slider6.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				value6 = (int) source.getValue();
				spinner6.setValue(value6);
			}
		});
	}

	private void initSpinners()
	{

		spinner1 = new JSpinner(new SpinnerNumberModel(120, 0, 255, 1));
		spinner1.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSpinner source = (JSpinner) e.getSource();
				value1 = (int) source.getValue();
				slider1.setValue(value1);
			}
		});

		spinner2 = new JSpinner(new SpinnerNumberModel(120, 0, 255, 1));
		spinner2.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSpinner source = (JSpinner) e.getSource();
				value2 = (int) source.getValue();
				slider2.setValue(value2);
			}
		});

		spinner3 = new JSpinner(new SpinnerNumberModel(120, 0, 255, 1));
		spinner3.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSpinner source = (JSpinner) e.getSource();
				value3 = (int) source.getValue();
				slider3.setValue(value3);
			}
		});

		spinner4 = new JSpinner(new SpinnerNumberModel(120, 0, 255, 1));
		spinner4.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSpinner source = (JSpinner) e.getSource();
				value4 = (int) source.getValue();
				slider4.setValue(value4);
			}
		});

		spinner5 = new JSpinner(new SpinnerNumberModel(120, 0, 255, 1));
		spinner5.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSpinner source = (JSpinner) e.getSource();
				value5 = (int) source.getValue();
				slider5.setValue(value5);
			}
		});

		spinner6 = new JSpinner(new SpinnerNumberModel(120, 0, 255, 1));
		spinner6.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSpinner source = (JSpinner) e.getSource();
				value6 = (int) source.getValue();
				slider6.setValue(value6);
			}
		});
	}

	private void setValues(int[] vals, ColorSpace t)
	{
		value1 = vals[0];
		value2 = vals[1];
		value3 = vals[2];
		value4 = vals[3];
		value5 = vals[4];
		value6 = vals[5];
		
		spinner1.setValue(value1);
		slider1.setValue(value1);
		
		spinner2.setValue(value2);
		slider2.setValue(value2);

		spinner3.setValue(value3);
		slider3.setValue(value3);

		spinner4.setValue(value4);
		slider4.setValue(value4);

		spinner5.setValue(value5);
		slider5.setValue(value5);

		spinner6.setValue(value6);
		slider6.setValue(value6);
	}

	public void init()
	{
		add(new JLabel("Color types"));
		typeBox = new JComboBox(ColorSpace.values());
		add(typeBox);

		add(new JLabel("Presets"));

		presetBox = new JComboBox(ColorPreset.values());
		presetBox.addItemListener(new ItemListener()
		{
			
			@Override
			public void itemStateChanged(ItemEvent event)
			{
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					ColorPreset pre = (ColorPreset) event.getItem();
					
					switch(pre)
					{
					case YELLOWTOTE_HLS:
						setValues(new int[] {21, 93, 52, 40, 156, 255}, ColorSpace.HLS);
						break;
					case GRAYTOTE_HLS:
						setValues(new int[] {4, 2, 2, 10, 10, 22}, ColorSpace.HLS);
						break;
					case GREENBIN_HLS:
						setValues(new int[] {43, 27, 23, 110, 104, 202}, ColorSpace.HLS);
						break;
					case GREENNOODLE_HLS:
						setValues(new int[] {8, 10, 12, 14, 16, 18}, ColorSpace.HLS);
						break;
					}
					
				}
			}
		});
		add(presetBox);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initSliders();
		initSpinners();

		add(new JLabel("Threshhold Low"));

		add(slider1);
		add(spinner1);

		add(slider2);
		add(spinner2);

		add(slider3);
		add(spinner3);

		add(new JLabel("Threshhold High"));

		add(slider4);
		add(spinner4);

		add(slider5);
		add(spinner5);

		add(slider6);
		add(spinner6);

		setBorder(BorderFactory.createTitledBorder("Colors"));

	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	public int getVal1()
	{
		return value1;
	}

	public int getVal2()
	{
		return value2;
	}

	public int getVal3()
	{
		return value3;
	}

	public int getVal4()
	{
		return value4;
	}

	public int getVal5()
	{
		return value5;
	}

	public int getVal6()
	{
		return value6;
	}

	public void update()
	{
		repaint();
	}
}
