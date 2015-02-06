package org.lemons5113.iris.gui;

import java.awt.FlowLayout;
import java.awt.Image;
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
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.process.SourceImageProc;
import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.networktables2.util.List;

//The heart and soul of this gui for VR. This is where the frame itself is made and formatted
//also,  ༼ つ ◕_◕ ༽つ Yay! Vision Recognition!
public class IRISGui 
{	
	private JFrame frame;
	private boolean open = true;
	//public IRISCamPanel panel;
	
    //public IRISSettingsPanel sett;
	
	SourceImageProc source;
	
	private ArrayList<ProcessPanel> panels = new ArrayList<ProcessPanel>();
    	
	public IRISGui()
	{			
		frame = new JFrame("5113 IRIS Systems 2015			          ༼ つ ◕_◕ ༽つ GIVE VISION RECOGNITION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                open = false;
            }
        } );
        
        JMenuBar menuBar = new JMenuBar();
        JMenu aboutMenu = new JMenu("Help");
        JMenuItem about = new JMenuItem("About IRIS");
        aboutMenu.add(about);
        menuBar.add(aboutMenu);
        frame.setJMenuBar(menuBar);
        /*
         * TODO:
         * Help:
         * About 5113/FRC
         * About IRIS
         * Guide/Documentation
         * 
         * Settings:
         * Disable Draw Display Image 
         * Split Pane or Tabbed Pane
         * Default Team Number
         * 
         * Misc Iris Menu:
         * Disable/Enable Force Process Unchanged Image Always
         * Force Process Unchanged Image Once
         * Load config file
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
                
        JTabbedPane processes = new JTabbedPane();

        for(ProcessPanel p : panels)
        {
        	processes.addTab(p.name, p);
        }
        
        
     
        
        frame.add(processes);
        
        
        
        
        frame.pack();
        frame.setVisible(true);
        
        
	}
	
	public void update()
	{
		for(ProcessPanel p : panels)
			p.update();
		
	}
	
	public boolean getIsOpened()
	{
		return open;
	}
	
	

}