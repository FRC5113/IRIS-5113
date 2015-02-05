package org.lemons5113.iris.process;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lemons5113.iris.IRISCamManager;
import org.lemons5113.iris.gui.settings.IRISSettingsPanel;
import org.lemons5113.iris.gui.settings.SettingsBase;

public class SourceImageProc extends ProcessBase {

	private IRISCamManager camera;
	private long lastUpdate;
	
	public SourceImageProc(SettingsBase sett)
	{
		this.sett = sett;
        camera = new IRISCamManager();
        ((IRISSettingsPanel) sett).camControlled = camera;
        
		try 
		{
			img = ImageIO.read(new File("icon.png"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	    ImageIO.setUseCache(false);

        
	}
	
	public void update()
	{
		if(lastUpdate < camera.getLastUpdate())
		{
			img = camera.getLastImage();
			lastUpdate = camera.getLastUpdate();
		}
	}
	
}
