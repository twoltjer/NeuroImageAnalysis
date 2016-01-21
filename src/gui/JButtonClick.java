package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logging.Log;
import system.Ops;

public class JButtonClick implements ActionListener {
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource().equals(JButtonManager.SCAN_WINDOW__CHANGE_DIRECTORY_BUTTON))
		{
			Log.write("Clicked changed directory button!", Log.DEBUG);
			GuiManager.changeDirectory();
		} else if(event.getSource().equals(JButtonManager.SCAN_WINDOW__SCAN_BUTTON)) {
			JButtonManager.SCAN_WINDOW__SCAN_BUTTON.setVisible(false);
			Ops.startScan();
		} else {
			//TODO: Write other buttons
		}
	}
}
