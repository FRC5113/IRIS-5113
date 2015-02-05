package org.lemons5113.iris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javafx.geometry.Dimension2DBuilder;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.lemons5113.iris.gui.settings.IRISSettingsPanel;
import org.lemons5113.iris.process.ProcessBase;

public class ProcessPanel extends JPanel {

	private IRISSettingsPanel sett;
	private ProcessBase proc;
	public String name = "Default Process Panel";
	
	private JPanel imageFrame;
	
	public ProcessPanel()
	{
	}
	
	public void init(IRISSettingsPanel sett, ProcessBase proc)
	{
		this.sett = sett;
		this.proc = proc;
		
		imageFrame = new JPanel() {
		
		    @Override
		    protected void paintComponent(Graphics g) 
		    {
		        super.paintComponent(g);        
		        g.drawImage(proc.getDisplayImage(), 0, 0, null);
			}
		};
		imageFrame.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		imageFrame.setPreferredSize(new Dimension(proc.getDisplayImage().getWidth(), proc.getDisplayImage().getHeight()));

		add(imageFrame);
		
		add(sett);
				
	}
	
	public void update()
	{
		sett.update();
		proc.update();
		imageFrame.repaint();
	}
}
