package org.lemons5113.iris;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
/**
 * 
 * @author Jacob Laurendeau
 * Responsible for outputting and managing of all data in the "NetworkTable" object.
 * This is the client side of the table management, and is NOT run on the robot's cRIO/roboRIO.
 */


public class IRISTableManager
{
	private NetworkTable table;

	private String robotIP = "roboRIO-5113.local";
	private String tableName = "IRISTable";

	private long timer = 0;

	public IRISTableManager()
	{
		init();
	}

	// TODO:
	// When this is set, a network variable is sent to the robot to force it to
	// give back the specified camera.
	public void RequestCameraName(String name)
	{

	}

	private void init()
	{
		try
		{
			NetworkTable.setClientMode();
			NetworkTable.setIPAddress(robotIP);
		} catch (Exception e)
		{
		}
		table = NetworkTable.getTable(tableName);
	}

	// ignore it. It totally isn't the code that will allow the robots in FIRST
	// to rise up and enslave the human race
	public void stupidTestPleaseIgnore()
	{
		if (System.currentTimeMillis() > timer + 1000)
		{
			timer = System.currentTimeMillis();
			double val = Math.random();
			table.putNumber("testValue", val);
			// System.out.println("new testvalue : " + val);
		}
	}

}