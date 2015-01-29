package org.lemons5113.iris.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.lemons5113.iris.IRISCamManager;
import org.opencv.core.Mat;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

//this is how the camera updates its image. AKA, how a bunch of static images make the human eye think something is moving. SCIENCE!
public class IRISCamPanel extends JPanel 
{	
	private BufferedImage img;
	
	private IRISCamManager mainCam;
			
    public IRISCamPanel(int width, int height) 
    {    
		//Status bar   	
        setPreferredSize(new Dimension(width, height));            
    }
   
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        img = mainCam.getLastImage();
        g.drawImage(img, 0, 0, null);
    }
    
    public void setCamera(IRISCamManager manager)
    {
    	mainCam = manager;
    }
    
    public IRISCamManager getCamera()
    {
    	return mainCam;
    }
    
    
    public void update()
    {    	
    	repaint();
    }
    
    //We are not using this yet, although it would allow us to compare the original photo to the filtered image
	public BufferedImage toBufferedImage(Mat m)
	{
	      int type = BufferedImage.TYPE_BYTE_GRAY;
	      if (m.channels() > 1 ) {
	          type = BufferedImage.TYPE_3BYTE_BGR;
	      }
	      int bufferSize = m.channels()*m.cols()*m.rows();
	      byte [] b = new byte[bufferSize];
	      m.get(0,0,b); // get all the pixels
	      BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	      final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	      System.arraycopy(b, 0, targetPixels, 0, b.length);  
	      return image;

	  }

}