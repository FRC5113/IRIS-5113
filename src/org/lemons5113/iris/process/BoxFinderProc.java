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

public class BoxFinderProc extends ProcessBase
{

	public BoxFinderProc(ColorPickerSett sett)
	{
		this.sett = sett;
	}
	
	
	@Override
	public void processMat(Mat mat)
	{
		Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_OPEN, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(8, 8)));
		Imgproc.morphologyEx(mat, mat, Imgproc.MORPH_CLOSE, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(8, 8)));

		try{
		
		List<MatOfPoint> points = new ArrayList<MatOfPoint>();
		
		Imgproc.findContours(mat.clone(), points, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR);

		int max = 0;
		List<Rect> rekt = new ArrayList<Rect>();
		for(int x = 0; x < points.size(); x++)
		{
			Mat contour = points.get(x);
			max = (int) Math.max(max, Imgproc.contourArea(contour));
			Rect r = Imgproc.boundingRect(points.get(x));
			rekt.add(r);
			Imgproc.rectangle(mat, r.br(), r.tl(), new Scalar(0, 0, 255), 3);
			Imgproc.putText(mat, "" + max, new Point(r.tl().x, r.tl().y - 5), Core.FONT_HERSHEY_PLAIN, 1, new Scalar(0, 0, 255));
			Imgproc.putText(mat, r.toString() + ", " + r.area(), new Point(r.tl().x, r.tl().y - 18), Core.FONT_HERSHEY_PLAIN, 1, new Scalar(0, 255, 0));
		}
		
		System.out.println(max);
		
		}
		catch(Exception e)
		{}
		

		img = mat2Img(mat);

	}

}