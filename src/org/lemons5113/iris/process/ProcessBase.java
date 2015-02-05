package org.lemons5113.iris.process;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.lemons5113.iris.gui.settings.SettingsBase;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ProcessBase {
	
	protected Mat mat;
	protected BufferedImage img;
	public static boolean updatedImage = false;
	protected SettingsBase sett;
	
	protected ProcessBase()
	{
	}
	
	public ProcessBase(SettingsBase sett)
	{
		this.sett = sett;
	}
	
	public BufferedImage getDisplayImage()
	{
		return img;
	}
	
	public Mat getProcessingMat()
	{
		return mat;
	}
	
	public void update()
	{
		
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
