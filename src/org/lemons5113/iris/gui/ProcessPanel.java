package org.lemons5113.iris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.gui.settings.SettingsBase;
import org.lemons5113.iris.process.ProcessBase;

public class ProcessPanel extends JPanel
{

	protected SettingsBase sett;
	public ProcessBase proc;
	public String name = "Default Process Panel";

	protected JPanel imageFrame;

	public ProcessPanel()
	{
	}

	public void init(SettingsBase sett, ProcessBase proc)
	{
		this.sett = sett;
		this.proc = proc;

		imageFrame = new JPanel()
		{

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(proc.getDisplayImage(), 0, 0, null);
			}
		};
		imageFrame.setPreferredSize(new Dimension(640, 480));
		imageFrame.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		// imageFrame.setPreferredSize(new
		// Dimension(proc.getDisplayImage().getWidth(),
		// proc.getDisplayImage().getHeight()));

		add(imageFrame);

		add(sett);

	}

	public void update()
	{
		sett.update();
		imageFrame.repaint();

		// if(proc.getDisplayImage() != null)

	}
}
