package org.lemons5113.iris;

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

public class IRISgui {
	
	private JFrame frame;
	private boolean open = true;
	IRISCamPanel panel;
	
	public IRISgui()
	{

		frame = new JFrame("5113 IRIS Systems 2015			          ༼ つ ◕_◕ ༽つ GIVE VISION RECOGNITION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                open = false;
            }
        } );
        
        
        frame.setBounds(0, 0, 600, 400);
        
		
        //image
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setVisible(true);
		picLabel.setLocation(50, 50);
		frame.add(picLabel);
		
        
        panel = new IRISCamPanel(500, 300);
        
        panel.setVisible(true);
        panel.setBounds(10, 10, 120, 120);
        frame.getContentPane().add(panel);
        
        
        
        frame.pack();
        frame.setVisible(true);
        
        
	}
	
	public void update()
	{
		panel.update();
	}
	
	public boolean getIsOpened()
	{
		return open;
	}
	
	

}
