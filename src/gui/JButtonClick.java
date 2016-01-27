package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import system.Ops;

/**
 * This class manages what happens when a button is clicked. Buttons that are
 * exempt from this class:
 * <ul>
 * <li>Buttons on the confirm close dialogs</li>
 * <li>So far, nothing else, but we're ready for more</li>
 * </ul>
 * 
 * @author twtduck
 * 
 */
public class JButtonClick implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(
				ScanWindow.changeDirectoryButton)) {
			GuiManager.changeDirectory();
		} else if (event.getSource().equals(
				ScanWindow.scanButton)) {
			ScanWindow.scanButton.setVisible(false);
			Ops.startScan();
		} else {
			// TODO: Write other buttons
		}
	}
}
