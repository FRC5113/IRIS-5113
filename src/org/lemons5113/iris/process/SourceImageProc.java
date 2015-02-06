package org.lemons5113.iris.process;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lemons5113.iris.IRISCamManager;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.gui.settings.SettingsBase;
import org.opencv.core.Mat;

public class SourceImageProc extends ProcessBase {

	private IRISCamManager camera;
	private long lastUpdate;
	
	public SourceImageProc(SettingsBase sett)
	{
		this.sett = sett;
        camera = new IRISCamManager();
        ((ImgSourceSett) sett).camControlled = camera;     
        
	}
	
	public void update()
	{		
		if(updatedImage)
		{
			img = camera.getLastImage();
			
			lastUpdate = camera.getLastUpdate();
			child.processMat(img2Mat(img));
			updatedImage = false;
		}
		
		if(lastUpdate < camera.getLastUpdate())
		{
			updatedImage = true;
		}
		
	}

	@Override
	public void processMat(Mat mat) {
		// TODO Auto-generated method stub
		
	}

}
