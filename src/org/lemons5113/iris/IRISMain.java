package org.lemons5113.iris;

import org.lemons5113.iris.gui.IRISGui;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.opencv.core.Core;

/**
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
 * IRIS is a tool by which camera code is sent to the laptop, processed in a program outside of the robot code,
 * put into a nice UI, and sent back to the robot program to be later interpreted as movement signals.
 *
 */

public class IRISMain
{
	private IRISGui gui;

	public static void main(String[] args)
	{
		new IRISMain();
	}

	public IRISMain()
	{
		System.out.println("IRIS has started!");
		initAll();

		long timer = System.currentTimeMillis();
		long interval = 24;
		while (gui.getIsOpened())
		{
			if(System.currentTimeMillis() - timer >= 50)
			{
				gui.update();
				timer = System.currentTimeMillis();
			}
			else
			{
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("IRIS has ended.");
		System.exit(0);
	}

	private void initAll()
	{
		// Import OpenCV stuffs
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		initUI();

		IRISTableManager.init();
	}

	private void initUI()
	{
		gui = new IRISGui();
	}

}
