package org.lemons5113.iris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.opencv.core.Mat;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class IRISCamPanel extends JPanel {
	
	private BufferedImage img;
	
	private IRISCamManager mainCam;
		
    public IRISCamPanel(int width, int height) {
        //image
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("icon_small.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setVisible(true);
		picLabel.setBounds(0, 0, 32, 32);
		add(picLabel);
        
		//Status bar
    	
        setPreferredSize(new Dimension(width, height));
    }
   
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        img = mainCam.getLastImage();
        g.drawImage(img, 0, 0, null);
    }
    
    public void setCamera(IRISCamManager manager)
    {
    	mainCam = manager;
    }
    
    
    public void update()
    {    	
    	repaint();
    }
    
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
