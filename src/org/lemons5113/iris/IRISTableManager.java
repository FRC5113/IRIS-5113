package org.lemons5113.iris;

import org.opencv.core.Rect;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
/**
 * 
 * @author Jacob Laurendeau
 * Responsible for outputting and managing of all data in the "NetworkTable" object.
 * This is the client side of the table management, and is NOT run on the robot's cRIO/roboRIO.
 * Mostly used for transferring box locations and setting camera configs.
 */


public class IRISTableManager
{
	private NetworkTable table;

	private String robotIP = "roboRIO-5113.local";
	private String tableName = "IRISTable";
	private boolean lastCamera = true;	//True = high, False = low

	private static IRISTableManager manager;
	
	public static IRISTableManager getIRISTableInstance()
	{
		return manager;
	}
	
	public IRISTableManager()
	{
		try
		{
			NetworkTable.setClientMode();
			NetworkTable.setIPAddress(robotIP);
		} catch (Exception e)
		{
		}
		table = NetworkTable.getTable(tableName);
		
		lastCamera = table.getBoolean("HighCamera");		
	}
	
	public void update()
	{
		boolean tempBool = table.getBoolean("HighCamera");
		if(tempBool != lastCamera)
		{
			lastCamera = tempBool;			
		}
	}
	
	public static void init()
	{		
		manager = new IRISTableManager();
	}
	
	
	//X, Y, W, H, D, A; Returns closest yellow tote as indicated bby the largest rect found
	public void setYellowToteData(Rect rekt, float distance, int angle)
	{
		String str;
		str = rekt.x + "," + rekt.y + "," + rekt.width + "," + rekt.height + "," + distance + "," + angle;
		table.putString("YellowToteData", str);
	}

}