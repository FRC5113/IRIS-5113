package org.lemons5113.iris.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.lemons5113.iris.IRISCamManager;

//This makes buttons. No seriously, thats practically all it does. Admittedly those buttons are useful but....
public class IRISSettingsPanel extends JPanel
{	
	private JButton button_resetConnection;
	
	private JLabel label_text_wirelessIP;
	private JTextField text_wirelessIP;
	
	private JLabel label_text_wiredIP;
	private JTextField text_wiredIP;
	
	private JLabel label_spinner_port;
	private JSpinner spinner_port;
	
	private JLabel label_spinner_fps;
	private JSpinner spinner_fps;
	
	public IRISCamManager camControlled;
	
	public void init()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		button_resetConnection = new JButton("Retry/Reset Connection");
		button_resetConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				initCamera();
			}
		});
		add(button_resetConnection);
		
		
		label_text_wirelessIP = new JLabel("Wireless ip adress (backup)");
		add(label_text_wirelessIP);
		text_wirelessIP = new JTextField("10.51.13.2", 20);
		add(text_wirelessIP);
		
		label_text_wiredIP = new JLabel("Wired local adress");
		add(label_text_wiredIP);
		text_wiredIP = new JTextField("roboRIO-5113.local", 20);	
		add(text_wiredIP);
		
		label_spinner_port = new JLabel("Port");
		add(label_spinner_port);
		spinner_port = new JSpinner(new SpinnerNumberModel(1180, 0, 999999, 1));
		add(spinner_port);
		
		label_spinner_fps = new JLabel("FPS");
		add(label_spinner_fps);
		spinner_fps = new JSpinner(new SpinnerNumberModel(24, 0, 999999, 1));
		add(spinner_fps);
		
		setBorder(BorderFactory.createTitledBorder("Settings"));

	}
	
	public void initCamera()
	{
		camControlled.init((Integer) spinner_fps.getValue(), (Integer) spinner_port.getValue(), text_wiredIP.getText(), text_wirelessIP.getText());
		
	}
	
	@Override
    protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
    }
	
    public void update()
    {    	
    	repaint();
    }
	
}