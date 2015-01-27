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

public class IRISCamManager
  implements Runnable
{
  public static final String NAME = "USB Webcam Viewer";
  public int fps = 30;
  private static final int PORT = 1180;
  private static final byte[] MAGIC_NUMBERS = { 1, 0, 0, 0 };
  private static final int SIZE_640x480 = 0;
  private static final int SIZE_320x240 = 1;
  private static final int SIZE_160x120 = 2;
  private static final int HW_COMPRESSION = -1;
  private BufferedImage frame = null;
  private final Object frameMutex = new Object();
  private String errorMessage = null;
  private Socket socket;
  private Thread thread;
  
  public void init()
  {    
	try {
		frame = ImageIO.read(new File("icon_small.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    this.thread = new Thread(this);
    this.thread.start();
    
    ImageIO.setUseCache(false);
  }
  
  public BufferedImage getLastImage()
  {
	  return frame;
  }
  
  public void disconnect()
  {
    this.thread.stop();
    if (this.socket != null) {
      try
      {
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
    	  System.out.println("at least you tried.." + Robot.getHost());
        this.socket = new Socket("roboRIO-5113.local", PORT);
        DataInputStream inputStream = new DataInputStream(this.socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(this.socket.getOutputStream());
  	  System.out.println("at least you tried.." + Robot.getHost());


        outputStream.writeInt(fps);
        outputStream.writeInt(-1);
        outputStream.writeInt(0);
        outputStream.flush();
        while (!Thread.interrupted())
        {
      	  System.out.println("at least you tried..2");

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
            System.out.println("works");
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
      }
    }
    catch (EOFException e)
    {
      if (this.errorMessage == null) {
        this.errorMessage = "Robot stopped returning images";
      }
    }
    catch (IOException e)
    {
      if (this.errorMessage == null) {
        this.errorMessage = e.getMessage();
      }
    }
    finally
    {
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
    System.out.println("Fatal Error: " + errorMessage);
  }
}
