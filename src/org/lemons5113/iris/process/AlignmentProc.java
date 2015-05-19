package org.lemons5113.iris.process;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lemons5113.iris.IRISCamManager;
import org.lemons5113.iris.IRISTableManager;
import org.lemons5113.iris.gui.settings.BoxPickerSett;
import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.gui.settings.SettingsBase;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.features2d.*;
import org.opencv.core.Rect;
import org.opencv.*;


/**
 * @author Jacob Laurendeau
 *
 * Draws lines on the image for driver alignment of robot and stuff
 *
 */
public class AlignmentProc extends ProcessBase
{
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	boolean partyModeEnabled = false;///////IN CASE OF VICTORY OR LOSS OF TEAM SPIRIT SET TO TRUE//
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////


	public AlignmentProc(ColorPickerSett sett)
	{
		this.sett = sett;
	}
	

	@Override
	public void processMat(Mat mat)
	{		
		try{
			for(float x = 0; x < 640; x += 80)
			{
				Imgproc.line(mat, new Point(x, 480), new Point(x, 0), new Scalar(180, 180, 180), 1);
			}
			for(float y = 0; y < 480; y += 80)
			{
				Imgproc.line(mat, new Point(640, y), new Point(0, y), new Scalar(180, 180, 180), 1);
			}
			
			Scalar color = new Scalar(0, 0, 255);
			if(partyModeEnabled)
			{
				Random rand = new Random();
				color =  new Scalar(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
				Imgproc.putText(mat, "GO REED GO!", new Point(500, 470), Core.FONT_HERSHEY_PLAIN, 1, color);
				Imgproc.putText(mat, "QUADRUPLED EFFICIENCY!", new Point(420, 20), Core.FONT_HERSHEY_PLAIN, 1, color);
				Imgproc.putText(mat, "STACK THOSE TOTES!", new Point(20, 20), Core.FONT_HERSHEY_PLAIN, 1, color);
				Imgproc.putText(mat, "ZERO PACKET LOSS!", new Point(20, 470), Core.FONT_HERSHEY_PLAIN, 1, color);
			}
			
			Imgproc.rectangle(mat, new Point(400, 320), new Point(240, 160), color, 3);
			Imgproc.rectangle(mat, new Point(400, 80), new Point(240, 0), color, 3);
			
			Mat tempImg = mat.clone();

			img = mat2Img(tempImg);
			
			//child.processMat(tempImg);
			
		}
		catch(Exception e)
		{
			System.err.println("errors occured lol dont care");
		}		
	}

}
