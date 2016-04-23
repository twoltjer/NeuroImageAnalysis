package com.nia.init;

import com.nia.control.Main;
import com.nia.elements.global.Log;
import com.nia.elements.global.Trigger;
import com.nia.elements.global.TriggerManager;
import com.nia.elements.instance.MainWindow;
import com.nia.elements.instance.SplashMaker;
import com.nia.exceptions.NoSuchTriggerException;
import com.nia.exceptions.TriggerIDExistsException;

public class InitSequence {
	public static void init() {
		// Start log
		Log.init("nialog");
		
		// Show splash
		SplashMaker sm = new SplashMaker();
		sm.run();
		
		// Add triggers
				try {
					TriggerManager.addTrigger(new Trigger("MAIN_WINDOW_DISPLAY"));
					TriggerManager.addTrigger(new Trigger("DISPLAY_FOLDER_CHOOSER"));
					TriggerManager.addTrigger(new Trigger("DO_NOTHING"));
				} catch (TriggerIDExistsException e) {
					Main.stop(e);
				}
				
		// Create threads and windows
		try {
			MainWindow.init();
		} catch (Exception e) {
			Main.stop(e);
		}
		
		
		
		// Close splash and trigger first window
		sm.splashDown();
		try {
			TriggerManager.getTriggerFromID("MAIN_WINDOW_DISPLAY").trigger();
		} catch (NoSuchTriggerException e) {
			Main.stop(e);
		}
	}
}
