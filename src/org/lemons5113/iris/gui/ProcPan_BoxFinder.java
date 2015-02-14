package org.lemons5113.iris.gui;

import org.lemons5113.iris.gui.settings.ColorPickerSett;
import org.lemons5113.iris.gui.settings.ImgSourceSett;
import org.lemons5113.iris.process.BoxFinderProc;
import org.lemons5113.iris.process.ColorThresholdProc;
import org.lemons5113.iris.process.SourceImageProc;

public class ProcPan_BoxFinder extends ProcessPanel
{

	public ProcPan_BoxFinder()
	{
		name = "Box Finder";
		ColorPickerSett set = new ColorPickerSett();
		set.init();
<<<<<<< HEAD
		//init(set, new BoxFinderProc(set));

=======
		init(set, new BoxFinderProc(set));
>>>>>>> origin/master
	}

}
