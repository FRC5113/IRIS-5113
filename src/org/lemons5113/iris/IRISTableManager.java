package org.lemons5113.iris;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


//Responsible for outputting and keeping track of all data in the "NetworkTable" object.
//This is the client side of the table management, NOT run on the robot side.

public class IRISTableManager {
	
	private NetworkTable table;
	
	private String robotIP = "roboRIO-5113.local";
	private String tableName = "IRISTable";
	
	private long timer = 0;
	
	public IRISTableManager()
	{
		init();
	}
	
	//TODO:
	//When this is set, a network variable is sent to the robot to force it to give back the specified camera.
	public void RequestCameraName(String name)
	{
		
	}
	
	private void init()
	{
		try
		{
			NetworkTable.setClientMode();
			NetworkTable.setIPAddress(robotIP);
		}
		catch(Exception e)
		{}
		table = NetworkTable.getTable(tableName);
	}
	
	public void stupidTestPleaseIgnore()
	{
		if(System.currentTimeMillis() > timer + 1000)
		{
			timer = System.currentTimeMillis();
			double val = Math.random();
			table.putNumber("testValue",val);
			//System.out.println("new testvalue : " + val);
		}
	}
	
	

}
