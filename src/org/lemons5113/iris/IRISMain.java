package org.lemons5113.iris;

import org.lemons5113.iris.gui.IRISGui;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.opencv.core.Core;

/*
 * Author: Jacob Laurendeau
 * Team 5113, Combustible Lemons IRIS system
 * 
 * Image
 * Recognition
 * Implementation
 * Systems
 * 
 * ...I fully intend to name all of our projects as acronyms of mythology names.
 * I thought it was cool anyways...
 *
 * IRIS is a method by which camera code is sent to the laptop, processed in a program outside of the robot code,
 * put into a nice UI, and sent back to the robot program.
 *
 */

public class IRISMain 
{	
	private IRISTableManager tables;
	private IRISGui gui;
	
	public static void main(String[] args) 
	{
		new IRISMain();
	}
	
	public IRISMain()
	{
		System.out.println("IRIS has started!");
		initAll();
		
		while(gui.getIsOpened())
		{
			tables.stupidTestPleaseIgnore();
			gui.update();
		}
		System.out.println("IRIS has ended.");
	}
	
	private void initAll()
	{
		//Import OpenCV stuffs
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                        
		initUI();
		
		tables = new IRISTableManager();
	}
	
	private void initUI()
	{
		gui = new IRISGui();
	}

}
