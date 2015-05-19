package org.lemons5113.iris.gui.settings;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.lemons5113.iris.IRISCamManager;
import org.lemons5113.iris.IRISTableManager;
import org.lemons5113.iris.process.SourceImageProc;

//This makes buttons. No seriously, thats practically all it does. Admittedly those buttons are useful but....
public class ImgSourceSett extends SettingsBase
{
	public enum states
	{
		Robot_Cam, Image_File, Computer_Cam
	};
	
	public enum AUTONPRESET
	{
		NONE, TOTE_CLEAR, TOTE_STEP
	};

	public JComboBox type = new JComboBox(states.values());
	
	public JComboBox auton = new JComboBox(AUTONPRESET.values());

	private JPanel roboCameraSetts;
	private JPanel imageFileSetts;
	private JPanel compCameraSetts;

	// Computer cam stuff
	private JButton button_resetCVCam;
	
	private JLabel label_computer_cam_ID;
	private JSpinner spinner_computer_cam_ID;

	// Robo cam stuff
	private JButton button_resetConnection;

	private JLabel label_text_wirelessIP;
	private JTextField text_wirelessIP;

	private JLabel label_text_wiredIP;
	private JTextField text_wiredIP;

	private JLabel label_spinner_port;
	private JSpinner spinner_port;

	private JLabel label_spinner_fps;
	private JSpinner spinner_fps;

	private SourceImageProc parent;

	public void setParent(SourceImageProc par)
	{
		parent = par;
	}

	// public IRISCamManager camControlled;

	public void init()
	{

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		roboCameraSetts = new JPanel();
		roboCameraSetts.setLayout(new BoxLayout(roboCameraSetts,
				BoxLayout.Y_AXIS));

		imageFileSetts = new JPanel();
		imageFileSetts
				.setLayout(new BoxLayout(imageFileSetts, BoxLayout.Y_AXIS));
		imageFileSetts.add(new JLabel("TODO"));

		compCameraSetts = new JPanel();
		compCameraSetts.setLayout(new BoxLayout(compCameraSetts,
				BoxLayout.Y_AXIS));

		add(new JLabel("Image Source"));
		add(type);
		add(new JLabel("Auton Preset Shortcut"));
		add(auton);
		add(new JLabel(" "));
		add(new JLabel(" "));

		button_resetConnection = new JButton("Retry/Reset Connection");
		button_resetConnection.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initCamera();
			}
		});
		roboCameraSetts.add(button_resetConnection);

		label_text_wirelessIP = new JLabel("Wireless ip adress (backup)");
		roboCameraSetts.add(label_text_wirelessIP);
		text_wirelessIP = new JTextField("10.51.13.2", 20);
		roboCameraSetts.add(text_wirelessIP);

		label_text_wiredIP = new JLabel("Wired local adress");
		roboCameraSetts.add(label_text_wiredIP);
		text_wiredIP = new JTextField("roboRIO-5113.local", 20);
		roboCameraSetts.add(text_wiredIP);

		label_spinner_port = new JLabel("Port");
		roboCameraSetts.add(label_spinner_port);
		spinner_port = new JSpinner(new SpinnerNumberModel(1180, 0, 999999, 1));
		roboCameraSetts.add(spinner_port);

		label_spinner_fps = new JLabel("FPS");
		roboCameraSetts.add(label_spinner_fps);
		spinner_fps = new JSpinner(new SpinnerNumberModel(8, 0, 999999, 1));
		roboCameraSetts.add(spinner_fps);

		// CV Cam stuff

		button_resetCVCam = new JButton("Retry/Reset Computer Connection");
		button_resetCVCam.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				initCameraCV();
			}
		});
		compCameraSetts.add(button_resetCVCam);
		
		label_computer_cam_ID = new JLabel("Computer Cam ID");
		compCameraSetts.add(label_computer_cam_ID);
		spinner_computer_cam_ID = new JSpinner(new SpinnerNumberModel(0, 0, 999999, 1));
		compCameraSetts.add(spinner_computer_cam_ID);

		roboCameraSetts.setVisible(true);
		imageFileSetts.setVisible(false);
		compCameraSetts.setVisible(false);

		add(roboCameraSetts);
		add(imageFileSetts);
		add(compCameraSetts);

		setBorder(BorderFactory.createTitledBorder("Settings"));

	}

	public void initCamera()
	{
		parent.initCamera((Integer) spinner_fps.getValue(),
				(Integer) spinner_port.getValue(), text_wiredIP.getText(),
				text_wirelessIP.getText());
	}

	public void initCameraCV()
	{
		parent.initCVCamera();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	
	public int getComputerCameraID()
	{
		return (Integer) spinner_computer_cam_ID.getValue();
	}

	public void update()
	{
		
		IRISTableManager.getIRISTableInstance().setAutonPresetData(auton.getSelectedItem().toString());
		
		if (type.getSelectedItem() == states.Computer_Cam)
		{
			roboCameraSetts.setVisible(false);
			imageFileSetts.setVisible(false);
			compCameraSetts.setVisible(true);
		} else if (type.getSelectedItem() == states.Robot_Cam)
		{
			roboCameraSetts.setVisible(true);
			imageFileSetts.setVisible(false);
			compCameraSetts.setVisible(false);
		} else if (type.getSelectedItem() == states.Image_File)
		{
			roboCameraSetts.setVisible(false);
			imageFileSetts.setVisible(true);
			;
			compCameraSetts.setVisible(false);
		}
		repaint();
	}

}