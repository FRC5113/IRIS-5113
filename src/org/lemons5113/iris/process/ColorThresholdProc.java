package org.lemons5113.iris.process;

import java.awt.Color;
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

	private float[] rgbToHsl(int red, int blue, int green)
	{
		float r = red / 255f;
		float g = green / 255f;
		float b = blue / 255f;

		float max = Math.max(Math.max(r, g), b);
		float min = Math.min(Math.min(r, g), b);

		float h;
		float s;
		float l = (max + min) / 2f;

		if (max == min)
		{
			h = s = 0;
		} else
		{
			float d = max - min;
			s = l > 0.5f ? d / (2 - max - min) : d / (max + min);

			if (max == r)
				h = (g - b) / d + (g < b ? 6f : 0);
			else if (max == g)
				h = (b - r) / d + 2f;
			else
				h = (r - g) / d + 4;

			h /= 6;
		}
		System.out.println("Red: " + red + "\nGreen: " + green + "\nBlue: " + blue);
		System.err.println((h * 180)+ ":" + (s * 255) + ":" + (l * 255));

		return new float[] { h * 180, s * 255, l * 255};

	}

	@Override
	public void processMat(Mat mat)
	{

		Mat imgConverted = new Mat();
		Mat thresholdedImg = new Mat();

		switch ((ColorPickerSett.type) ((ColorPickerSett) sett).typeBox
				.getSelectedItem())
		{
		case HLS:
			Imgproc.cvtColor(mat, imgConverted, Imgproc.COLOR_BGR2HLS);

			float[] hlsHigh = new float[3];
			float[] hlsLow = new float[3];

			hlsHigh = rgbToHsl(ColorPickerSett.colorHigh.getRed(),
					ColorPickerSett.colorHigh.getBlue(),
					ColorPickerSett.colorHigh.getGreen());
			hlsLow = rgbToHsl(ColorPickerSett.colorLow.getRed(),
					ColorPickerSett.colorLow.getBlue(),
					ColorPickerSett.colorLow.getGreen());

			Core.inRange(imgConverted, new Scalar(hlsLow[0], hlsLow[1],
					hlsLow[2]), new Scalar(hlsHigh[0], hlsHigh[1], hlsHigh[2]),
					thresholdedImg);

			break;
		case BGR:
			Core.inRange(imgConverted,
					new Scalar(ColorPickerSett.colorLow.getBlue(),
							ColorPickerSett.colorLow.getGreen(),
							ColorPickerSett.colorLow.getRed()), new Scalar(
							ColorPickerSett.colorHigh.getBlue(),
							ColorPickerSett.colorHigh.getGreen(),
							ColorPickerSett.colorHigh.getRed()), thresholdedImg);

			break;
		case HSV:

			// HSB is the same as HSB.

			Imgproc.cvtColor(mat, imgConverted, Imgproc.COLOR_BGR2HSV);

			float[] hsvHigh = new float[3];
			float[] hsvLow = new float[3];

			Color.RGBtoHSB(ColorPickerSett.colorHigh.getRed(),
					ColorPickerSett.colorHigh.getGreen(),
					ColorPickerSett.colorHigh.getBlue(), hsvHigh);
			Color.RGBtoHSB(ColorPickerSett.colorLow.getRed(),
					ColorPickerSett.colorLow.getGreen(),
					ColorPickerSett.colorLow.getBlue(), hsvLow);

			Core.inRange(imgConverted, new Scalar(hsvLow[0], hsvLow[1],
					hsvLow[2]), new Scalar(hsvHigh[0], hsvHigh[1], hsvHigh[2]),
					thresholdedImg);

			break;
		}

		Imgproc.cvtColor(thresholdedImg, mat, Imgproc.COLOR_GRAY2BGR);

		img = mat2Img(mat);

	}

}
