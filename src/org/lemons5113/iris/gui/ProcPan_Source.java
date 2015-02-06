package org.lemons5113.iris.gui;

import java.awt.Dimension;

import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.process.ColorThresholdProc;
import org.lemons5113.iris.process.SourceImageProc;

public class ProcPan_Source extends ProcessPanel {

	public ProcPan_Source() {
		name = "Image Source";
        ImgSourceSett set = new ImgSourceSett();
        set.init();
        init(set, new SourceImageProc(set));
	}
	
	public void update()
	{
		sett.update();
		((SourceImageProc) proc).update();
		imageFrame.repaint();
		//if(proc.getDisplayImage() != null)
		//	imageFrame.setPreferredSize(new Dimension(512, 512));
	}

}
