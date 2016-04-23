package com.nia.elements.global;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.nia.elements.instance.MainWindow;
import com.nia.exceptions.NoSuchTriggerException;

/**
 * This class is to manage the Triggers that are all interspersed throughout the
 * program. It is also what decides what happens when a Trigger is triggered.
 * 
 * @author Thomas Woltjer
 */
public abstract class TriggerManager {

	private static ArrayList<Trigger> triggers;

	/**
	 * Instantiates the trigger list if necessary. Then, adds the argument
	 * Trigger to the trigger list.
	 * 
	 * @param trigger
	 *            The trigger to add to the trigger list.
	 */
	public static void addTrigger(Trigger trigger) {
		if (triggers == null) {
			triggers = new ArrayList<Trigger>();
		}
		triggers.add(trigger);
	}

	/**
	 * Gets a Trigger from the ID associated with it by querying the list of
	 * registered triggers. If no Trigger exists that matches the given
	 * triggerID, this returns null.
	 * 
	 * @param triggerID
	 *            The Trigger ID that matches with the Trigger being requested
	 * @return the Trigger that matches the given Trigger ID. If there is no
	 *         such Trigger, this method returns null.
	 */
	public static Trigger getTriggerFromID(String triggerID) throws NoSuchTriggerException {
		for (Trigger trigger : triggers) {
			if (trigger.getTriggerID().equals(triggerID))
				return trigger;
		}
		throw new NoSuchTriggerException();
	}

	/**
	 * This method is significantly longer than the other methods in this class.
	 * For each trigger that could exist, it declares an action for that trigger
	 * to perform.
	 * 
	 * @param trigger
	 *            The trigger performing an action
	 */
	protected static void trigger(Trigger trigger) throws NoSuchTriggerException {
		switch (trigger.getTriggerID()) {
			case "MAIN_WINDOW_DISPLAY":
				MainWindow.display();
					break;
			case "DISPLAY_FOLDER_CHOOSER":
				MainWindow.folderChooserPanel.setBackground(Color.blue);
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				MainWindow.folderChooserPanel.add(fc);
					break;
			case "DO_NOTHING":
					break;
			default: throw new NoSuchTriggerException();
		}
	}
}
