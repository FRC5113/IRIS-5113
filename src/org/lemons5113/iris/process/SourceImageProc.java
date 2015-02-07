package org.lemons5113.iris.process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lemons5113.iris.IRISCamManager;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.gui.settings.SettingsBase;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import sun.misc.GC.LatencyRequest;

public class SourceImageProc extends ProcessBase
{

	private IRISCamManager camera;
	private long lastUpdate;
	private VideoCapture cvCam;
	private Mat lastCapturedMat;

	public SourceImageProc(SettingsBase sett)
	{
		this.sett = sett;
		((ImgSourceSett) sett).setParent(this);

		camera = new IRISCamManager();
	}

	public void initCamera(int fps, int port, String usbConnection,
			String wirelessConnection)
	{
		camera.init(fps, port, usbConnection, wirelessConnection);
		cvCam.release();
		cvCam = null;
	}

	public void initCVCameraImage()
	{

	}

	public void initCVCamera()
	{
		cvCam = new VideoCapture(0);
		camera.disconnect();
	}

	public void update()
	{
		switch ((ImgSourceSett.states) ((ImgSourceSett) sett).type
				.getSelectedItem())
		{

		case Robot_Cam:
			if (updatedImage)
			{
				img = camera.getLastImage();
				lastUpdate = camera.getLastUpdate();
				child.processMat(img2Mat(img));

				updatedImage = false;
			}
			if (lastUpdate < camera.getLastUpdate())
			{
				updatedImage = true;
			}

			break;

		case Computer_Cam:
			if (cvCam != null)
			{
				Mat temp = new Mat();
				updatedImage = cvCam.read(temp);
				if(updatedImage)
				{
					lastCapturedMat = temp.clone();
					child.processMat(temp);					
				}
			}
			updatedImage = false;

			break;

		case Image_File:
			break;

		}
	}

	public BufferedImage getDisplayImage()
	{
		switch ((ImgSourceSett.states) ((ImgSourceSett) sett).type
				.getSelectedItem())
		{

		case Robot_Cam:
			return img;
		case Computer_Cam:
			if(cvCam != null && lastCapturedMat != null)
				return mat2Img(lastCapturedMat);
		case Image_File:
			return null;
		}

		return img;
	}

	@Override
	public void processMat(Mat mat)
	{
		// TODO Auto-generated method stub

	}

}
