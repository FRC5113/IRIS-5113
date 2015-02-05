package org.lemons5113.iris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Arrays;

import javax.imageio.ImageIO;

import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//This is the complicated bit. This is the code that connects to the camera, formats the camera for our use, and a bucket full of error checks.
public class IRISCamManager
  implements Runnable
{
	//lots o' variables
  public int fps = 30;//Frames per second
  private int port = 1180;
  private String usbConnectionIP = "roboRIO-5113.local";
  private String wirelessConnectionIP = "10.51.13.2";
  private static final byte[] MAGIC_NUMBERS = { 1, 0, 0, 0 };//The numbers that tell the [RobotRio](not sure) to expect to be working with a camera
  private static final int SIZE_640x480 = 0;
  private static final int SIZE_320x240 = 1;
  private static final int SIZE_160x120 = 2;
  private static final int HW_COMPRESSION = -1;//can either be -1 or 1, (Also needs to be clarified)
  private BufferedImage frame = null;
  private final Object frameMutex = new Object();
  public String errorMessage = null;
  private Socket socket;
  private Thread thread;
  private long lastUpdate = 0;
  
  public long getLastUpdate()
  {
	  return lastUpdate;
  }
  
  public void init(int fps, int port, String usbConnection, String wirelessConnection)
  {
	  
	System.out.println("Init/Retrying camera");
	  
	this.fps = fps;
	this.port = port;
	this.usbConnectionIP = usbConnection;
	this.wirelessConnectionIP = wirelessConnection;

	
	disconnect();
	
    this.thread = new Thread(this);
    this.thread.start();
    
  }
  
  public BufferedImage getLastImage()
  {
	  return frame;
  }
  
  public void disconnect()
  {
	if(thread != null)
	{
		this.thread.stop();//TODO we can make this just in general better
	}
    if (this.socket != null) {
      try
      {
    	  System.out.println("Disconnecting, closing socket...");
        this.socket.close();
      }
      catch (IOException e) {}
    }
  }
  
  public void run()
  {
    try
    {
      for(;;)
      {
    	  
    	System.out.println("Trying to connect to " + usbConnectionIP + ":" + port + "...");
    	
    	DataInputStream inputStream = null;
    	DataOutputStream outputStream = null;
    	
    	try
    	{
    		this.socket = new Socket(usbConnectionIP, port);
        	inputStream = new DataInputStream(this.socket.getInputStream());
        	outputStream = new DataOutputStream(this.socket.getOutputStream());
        }
    	catch(Exception e)
    	{
    		System.out.println("Failed to connect to wired connetion, retrying with wireless...");
        	System.out.println("Trying to connect to " + wirelessConnectionIP + ":" + port + "...");

    		this.socket = new Socket(wirelessConnectionIP, port);
        	inputStream = new DataInputStream(this.socket.getInputStream());
        	outputStream = new DataOutputStream(this.socket.getOutputStream());
    	}

        outputStream.writeInt(fps);
        outputStream.writeInt(-1);
        outputStream.writeInt(0);
        outputStream.flush();
        
        while (!Thread.interrupted())
        {
          byte[] magic = new byte[4];
          inputStream.readFully(magic);
          int size = inputStream.readInt();
          
          assert (Arrays.equals(magic, MAGIC_NUMBERS));
          



          byte[] data = new byte[size];
          inputStream.readFully(data);
          
          assert ((data.length >= 4) && (data[0] == 255) && (data[1] == 216) && (data[(data.length - 2)] == 255) && (data[(data.length - 1)] == 217));
          synchronized (this.frameMutex)
          {
            if (this.frame != null) {
              this.frame.flush();
            }
            this.frame = ImageIO.read(new ByteArrayInputStream(data));
            this.errorMessage = null;
                        
            lastUpdate = System.currentTimeMillis();
            
          }
        }
        if (this.socket != null) {
          try
          {
            this.socket.close();
          }
          catch (IOException e) {}
        }
        try
        {
          Thread.sleep(1000L);
        }
        catch (InterruptedException e1) {}
      }
    }
    catch (ConnectException e)
    {
      if (this.errorMessage == null) {
        this.errorMessage = e.getMessage();
        System.out.println("Error: " + errorMessage);
      }
    }
    catch (EOFException e)
    {
      if (this.errorMessage == null) {
        this.errorMessage = "Robot stopped returning images";
        System.out.println("Error: " + errorMessage);
      }
    }
    catch (IOException e)
    {
      if (this.errorMessage == null) {
        this.errorMessage = e.getMessage();
        System.out.println("Error: " + errorMessage);
      }
    }
    finally
    {
      if (this.socket != null) {
        try
        {
          this.socket.close();
        }
        catch (IOException e) {
            this.errorMessage = e.getMessage();
            System.out.println("Error: " + errorMessage);
        }
      }
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException e1) {
          this.errorMessage = e1.getMessage();
          System.out.println("Error: " + errorMessage);
      }
    }
    System.err.println("FATAL ERROR (NEEDS RETRY): " + errorMessage);
  }
}
