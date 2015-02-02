package org.lemons5113.iris.gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;

//The heart and soul of this gui for VR. This is where the frame itself is made and formatted
//also,  ༼ つ ◕_◕ ༽つ Yay! Vision Recognition!
public class IRISGui 
{	
	private JFrame frame;
	private boolean open = true;
	public IRISCamPanel panel;
	
    public IRISSettingsPanel sett;
    
    public IRISColorPicker colors;
	
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
        frame.setLayout(new FlowLayout());
        frame.setBounds(0, 0, 600, 600);
        
	    sett = new IRISSettingsPanel();
	    sett.init();
	    frame.add(sett);
	    
        
        panel = new IRISCamPanel(500, 300);        
        panel.setSize(120, 120);
        frame.add(panel); 
        
        colors = new IRISColorPicker();
        colors.init();
        frame.add(colors);
        
        
        
        
        frame.pack();
        frame.setVisible(true);
        
        
	}
	
	public void update()
	{
		panel.update();
		sett.update();
		colors.update();
	}
	
	public boolean getIsOpened()
	{
		return open;
	}
	
	

}