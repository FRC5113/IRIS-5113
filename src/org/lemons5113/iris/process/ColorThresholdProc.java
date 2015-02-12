package org.lemons5113.iris.process;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lemons5113.iris.IRISCamManager;
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

public class ColorThresholdProc extends ProcessBase
{

	public ColorThresholdProc(ColorPickerSett sett)
	{
		this.sett = sett;
	}
	
	
	@Override
	public void processMat(Mat mat)
	{

		Mat imgConverted = new Mat();
		Mat thresholdedImg = new Mat();
		

		
		int col1 = ((ColorPickerSett)sett).getVal1();
		int col2 = ((ColorPickerSett)sett).getVal2();
		int col3 = ((ColorPickerSett)sett).getVal3();
		
		int col4 = ((ColorPickerSett)sett).getVal4();
		int col5 = ((ColorPickerSett)sett).getVal5();
		int col6 = ((ColorPickerSett)sett).getVal6();
		
		float[] colorHigh = new float[] {col4, col5, col6};
		float[] colorLow = new float[] {col1, col2, col3};

		switch ((ColorPickerSett.ColorSpace) ((ColorPickerSett) sett).typeBox
				.getSelectedItem())
		{
		case HLS:
			Imgproc.cvtColor(mat, imgConverted, Imgproc.COLOR_BGR2HLS);

			Core.inRange(imgConverted, new Scalar(colorLow[0], colorLow[1],
					colorLow[2]), new Scalar(colorHigh[0], colorHigh[1], colorHigh[2]),
					thresholdedImg);

			break;
		case BGR:

			Core.inRange(mat, new Scalar(colorLow[0], colorLow[1],
					colorLow[2]), new Scalar(colorHigh[0], colorHigh[1], colorHigh[2]),
					thresholdedImg);

			break;
		case HSV:

			// HSB is the same as HSB.

			Imgproc.cvtColor(mat, imgConverted, Imgproc.COLOR_BGR2HSV);

			Core.inRange(imgConverted, new Scalar(colorLow[0], colorLow[1],
					colorLow[2]), new Scalar(colorHigh[0], colorHigh[1], colorHigh[2]),
					thresholdedImg);
			
			break;
		}
		
		Mat tempImg = thresholdedImg.clone();
		Imgproc.cvtColor(tempImg, tempImg, Imgproc.COLOR_GRAY2BGR);

		img = mat2Img(tempImg);
		
		child.processMat(thresholdedImg);

	}

}
