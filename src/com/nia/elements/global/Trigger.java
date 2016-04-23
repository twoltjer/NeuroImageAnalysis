package com.nia.elements.global;

import com.nia.control.Main;
import com.nia.exceptions.NoSuchTriggerException;
import com.nia.exceptions.TriggerIDExistsException;

/**
 * Triggers are used to make one part of the program call another. This is often
 * used to transition stages in the sequence of the main program itself, but can
 * be used for a great variety of purposes.
 * 
 * @author Thomas Woltjer
 */
public class Trigger {
	private String triggerID;

	/**
	 * Creates a trigger, and assigns it an ID. The Trigger ID can't change. Also, 
	 * @param triggerID
	 */
	public Trigger(String triggerID) throws TriggerIDExistsException  {
		this.triggerID = triggerID;
	}

	public void trigger() {
		try {
			TriggerManager.trigger(this);
		} catch (NoSuchTriggerException e) {
			Main.stop(e);
		}
		
	}

	public String getTriggerID() {
		return this.triggerID;
	}
}
