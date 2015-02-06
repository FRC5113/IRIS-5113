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
import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.*;
import org.opencv.objdetect.Objdetect;

import com.ni.vision.NIVision.ThresholdData;

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
    
    protected BufferedImage process()
    {   	
    	
    	Mat m = img2Mat(img);
    	System.out.println("a " + CvType.typeToString(m.type()));

    	
    	Mat imgHSV = new Mat();
    	
    	Imgproc.cvtColor(m, imgHSV, Imgproc.COLOR_BGR2HLS);
    	//Imgproc.cvtColor(imgHSV, m, Imgproc.COLOR_GRAY2BGR);
    	
    //	System.out.println("a2 " + CvType.typeToString(imgHSV.type()));


    	Mat thresholdedImg = new Mat();
    	
//    	Core.inRange(m, new Scalar(38, 50, 50), new Scalar(255, 100, 100), m);
    	
    	Core.inRange(imgHSV, 
    			new Scalar(ColorPickerSett.colorLow.getBlue(), ColorPickerSett.colorLow.getGreen(), ColorPickerSett.colorLow.getRed()),
    			new Scalar(ColorPickerSett.colorHigh.getBlue(), ColorPickerSett.colorHigh.getGreen(), ColorPickerSett.colorHigh.getRed()),    			thresholdedImg);
    //	System.out.println("b " + CvType.typeToString(thresholdedImg.type()));
    	
    	Imgproc.cvtColor(thresholdedImg, m, Imgproc.COLOR_GRAY2BGR);
    	//System.out.println("c " + CvType.typeToString(m.type()));
    	
    	Mat threshold_output;
    	
    	
    	
    	BufferedImage image2 = mat2Img(m);
      	
    	
    	//Mat temp = new Mat();
    	//temp.convertTo(temp, core.);
    	//Imgproc.cvtColor(mat, temp, Imgproc.COLOR_BGR2HSV);
    	//Core.inRange(mat, new Scalar(38, 50, 50), new Scalar(255, 100, 100), temp);
    	//Imgproc.threshold(temp, temp, 127, 255, Imgproc.THRESH_TOZERO);
    	
    	    	
    	
    	
    	return image2;
    }
   
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
    	
        img = mainCam.getLastImage();

       try
       {
    	   if(img != null)
    		   img = process();
       }
       catch(Exception e)
       {
       e.printStackTrace();
       }
        
        //img = mainCam.getLastImage();
        
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
    
    public static BufferedImage mat2Img(Mat mat)
    {
    	byte[] data = new byte[mat.rows()*mat.cols()*(int)(mat.elemSize())];
    	mat.get(0, 0, data);
    	if (mat.channels() == 3) {
    	 for (int i = 0; i < data.length; i += 3) {
    	  byte temp = data[i];
    	  data[i] = data[i + 2];
    	  data[i + 2] = temp;
    	 }
    	}
		BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_3BYTE_BGR);
		image.getRaster().setDataElements(0,  0,  mat.cols(), mat.rows(), data);
		return image;
    } 



    public static Mat img2Mat(BufferedImage image)
    {
    	byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    	Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
    	mat.put(0, 0, data);
    	return mat;
    }
}