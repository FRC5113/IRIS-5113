package org.lemons5113.iris.gui;

import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.process.ColorThresholdProc;
import org.lemons5113.iris.process.SourceImageProc;

public class ProcPan_ColorThresh extends ProcessPanel {

	public ProcPan_ColorThresh() {
		name = "Image Source";
        ColorPickerSett set = new ColorPickerSett();
        set.init();
        init(set, new ColorThresholdProc(set));
        
	}

}
