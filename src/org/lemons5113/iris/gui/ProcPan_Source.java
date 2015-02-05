package org.lemons5113.iris.gui;

import org.lemons5113.iris.gui.settings.IRISSettingsPanel;
import org.lemons5113.iris.process.SourceImageProc;

public class ProcPan_Source extends ProcessPanel {

	public ProcPan_Source() {
		name = "Image Source";
        IRISSettingsPanel set = new IRISSettingsPanel();
        set.init();
        init(set, new SourceImageProc(set));
	}

}
