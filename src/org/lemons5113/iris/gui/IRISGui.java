package org.lemons5113.iris.gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.PopupFactory;

import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.process.SourceImageProc;
import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.networktables2.util.List;

class MenuActionListener implements ActionListener
{
        public void actionPerformed(ActionEvent e)
        {
                System.out.println("Selected: " + e.getActionCommand());
        }
}

//The heart and soul of this gui for VR. This is where the frame itself is made and formatted
//also,  ༼ つ ◕_◕ ༽つ Yay! Vision Recognition!
public class IRISGui
{
	private JFrame frame;
	private boolean open = true;
	// public IRISCamPanel panel;

	// public IRISSettingsPanel sett;

	SourceImageProc source;

	private ArrayList<ProcessPanel> panels = new ArrayList<ProcessPanel>();

	public IRISGui()
	{
		frame = new JFrame(
				"5113 IRIS Systems 2015		    Version [1.337b]	          ༼ つ ◕_◕ ༽つ GIVE VISION RECOGNITION");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				open = false;
			}
		});

		ImageIcon icon5113 = new ImageIcon("icon_small.png");
	       
        JMenuBar menuBar = new JMenuBar();
        JMenu aboutMenu = new JMenu("Help");
        JMenuItem about = new JMenuItem("About IRIS");
        JMenu kyleDrouin = new JMenu("Yo Boi");
        JMenu settingMenu = new JMenu("Settings");
        JMenu miscMenu = new JMenu("Misc IRIS Menu");
       
        JPopupMenu popup5113 = new JPopupMenu();
        popup5113.add(new JTextArea("Test string"));
        PopupFactory fact = PopupFactory.getSharedInstance();
       
        JMenuItem about5113 = new JMenuItem("About 5113/FRC");
        about5113.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                        maybeShowPopup(e);
                }
               
                public void mouseReleased(MouseEvent e){
                        maybeShowPopup(e);
                }
               
                private void maybeShowPopup(MouseEvent e){
                        if(e.isPopupTrigger()){
                                popup5113.show(e.getComponent(), e.getX(), e.getY());
                        }
                }
                });
        JMenuItem aboutIRIS = new JMenuItem("About IRIS");
        aboutIRIS.addActionListener(new MenuActionListener());
        JMenuItem aboutGuide = new JMenuItem("Guide/Documentation");
        aboutGuide.addActionListener(new MenuActionListener());
        JMenuItem aboutKyle = new JMenuItem("About Kyle Drouin");
        aboutKyle.addActionListener(new MenuActionListener());
        JMenuItem settingDisable = new JMenuItem("Disable Draw Display Image");
        settingDisable.addActionListener(new MenuActionListener());
        JMenuItem settingSplit = new JMenuItem("Split Pane or Tabbed Pane");
        settingSplit.addActionListener(new MenuActionListener());
        JMenuItem settingTeamNumber = new JMenuItem("Default Team Number");
        settingTeamNumber.addActionListener(new MenuActionListener());
        JMenuItem miscEnableDisable = new JMenuItem("Disable/Enable Force Process Unchanged Image Always");
        miscEnableDisable.addActionListener(new MenuActionListener());
        JMenuItem miscForce = new JMenuItem("Force Process Unchanged Image Once");
        miscForce.addActionListener(new MenuActionListener());
        JMenuItem miscLoad = new JMenuItem("Load Config File");
        miscLoad.addActionListener(new MenuActionListener());
               
        aboutMenu.add(about5113);
        aboutMenu.add(aboutIRIS);
        kyleDrouin.add(aboutKyle);
        aboutMenu.add(aboutGuide);
        settingMenu.add(settingDisable);
        settingMenu.add(settingSplit);
        settingMenu.add(settingTeamNumber);
        miscMenu.add(miscEnableDisable);
        miscMenu.add(miscForce);
        miscMenu.add(miscLoad);
       
        menuBar.add(aboutMenu);
        menuBar.add(settingMenu);
        menuBar.add(miscMenu);
        menuBar.add(kyleDrouin);
           
        about5113.setIcon(icon5113);
        frame.setJMenuBar(menuBar);
		/*
		 * TODO: Help: About 5113/FRC About IRIS Guide/Documentation
		 * 
		 * Settings: Disable Draw Display Image Split Pane or Tabbed Pane
		 * Default Team Number
		 * 
		 * Misc Iris Menu: Disable/Enable Force Process Unchanged Image Always
		 * Force Process Unchanged Image Once Load config file
		 * 
		 * Save all info in file
		 * 
		 * Vary HLS/BGR/HSV/Other threshholding
		 */

		ProcessPanel panel1 = new ProcPan_Source();
		panels.add(panel1);

		ProcessPanel panel2 = new ProcPan_ColorThresh();
		panel1.proc.setChild(panel2.proc);
		panels.add(panel2);
		
//		ProcessPanel panel3 = new ProcPan_BoxFinder();
//		panel2.proc.setChild(panel3.proc);
//		panels.add(panel3);

		JTabbedPane processes = new JTabbedPane();

		for (ProcessPanel p : panels)
		{
			// In case the screen is too small, put the whole frame in the
			JScrollPane s = new JScrollPane(p);
			processes.addTab(p.name, s);
		}

		frame.add(processes);

		// frame.pack();
		frame.setSize(1000, 700);
		frame.setVisible(true);

	}

	public void update()
	{
		for (ProcessPanel p : panels)
		{
			p.update();
		}
	}

	public boolean getIsOpened()
	{
		return open;
	}

}