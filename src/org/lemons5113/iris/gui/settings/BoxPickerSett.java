package org.lemons5113.iris.gui.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoxPickerSett extends SettingsBase 
{
	private JSlider slider1;
	private JButton button;
	
	public void init()
	{
	button = new JButton();
	button.addActionListener(new ActionListener()
		{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}});
	
	/*
	slider1 = new JSlider(JSlider.HORIZONTAL, 0, 255, 120);
	slider1.addChangeListener(new ChangeListener()
	 {
		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			int value1 = (int) source.getValue();
		}
	 });*/
		
	}

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		init();
		
	}
}