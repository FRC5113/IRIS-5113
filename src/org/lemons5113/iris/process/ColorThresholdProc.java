package org.lemons5113.iris.process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lemons5113.iris.IRISCamManager;
import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.gui.settings.SettingsBase;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ColorThresholdProc extends ProcessBase
{
	
	public ColorThresholdProc(ColorPickerSett sett)
	{
		this.sett = sett;        
	}

	@Override
	public void processMat(Mat mat) {		
    	System.out.println("a " + CvType.typeToString(mat.type()));

    	
    	Mat imgHSV = new Mat();
    	
    	Imgproc.cvtColor(mat, imgHSV, Imgproc.COLOR_BGR2HLS);
    	//Imgproc.cvtColor(imgHSV, m, Imgproc.COLOR_GRAY2BGR);
    	
    //	System.out.println("a2 " + CvType.typeToString(imgHSV.type()));


    	Mat thresholdedImg = new Mat();
    	
//    	Core.inRange(m, new Scalar(38, 50, 50), new Scalar(255, 100, 100), m);
    	
    	Core.inRange(imgHSV, 
    			new Scalar(ColorPickerSett.colorLow.getBlue(), ColorPickerSett.colorLow.getGreen(), ColorPickerSett.colorLow.getRed()),
    			new Scalar(ColorPickerSett.colorHigh.getBlue(), ColorPickerSett.colorHigh.getGreen(), ColorPickerSett.colorHigh.getRed()),    			thresholdedImg);
    //	System.out.println("b " + CvType.typeToString(thresholdedImg.type()));
    	
    	Imgproc.cvtColor(thresholdedImg, mat, Imgproc.COLOR_GRAY2BGR);
    	//System.out.println("c " + CvType.typeToString(m.type()));
    	    	
    	
    	
    	img = mat2Img(mat);
      	
	}
	
}
