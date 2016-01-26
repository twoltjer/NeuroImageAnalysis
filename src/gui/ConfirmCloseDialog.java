package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import logging.Log;
import system.Config;

/**
 * Class for managing a confirm close dialog. (Surprise, surprise)
 * 
 * @author twtduck
 * 
 */
public class ConfirmCloseDialog {
	private JFrame parent;
	private JFrame dialogFrame;
	private JButton yesButton;
	private JButton noButton;
	private JLabel message;

	/**
	 * Creates the dialog.
	 * 
	 * @param parent
	 *            the parent window, that is closed if the yes button is
	 *            clicked.
	 * @param message
	 *            the message to display in the dialog
	 */
	public ConfirmCloseDialog(JFrame parent, String message) {
		// Instantiate instance variables
		this.parent = parent;
		this.message = new JLabel(message);
		this.yesButton = new JButton("Yes");
		this.noButton = new JButton("No");

		// Create window and set layout
		this.dialogFrame = new JFrame(Config.PROGRAM_NAME__TITLE_BAR);
		this.dialogFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container pane = this.dialogFrame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints dialogConstraints = new GridBagConstraints();

		// Add elements
		dialogConstraints.fill = GridBagConstraints.BOTH;
		dialogConstraints.weightx = 1;
		dialogConstraints.gridwidth = 2;
		pane.add(this.message, dialogConstraints);
		dialogConstraints.gridwidth = 1;
		dialogConstraints.gridy = 1;
		pane.add(this.yesButton, dialogConstraints);
		dialogConstraints.gridx = 1;
		pane.add(this.noButton, dialogConstraints);

		// Add action listeners
		this.yesButton.addActionListener(new ButtonClick());
		this.noButton.addActionListener(new ButtonClick());

		this.dialogFrame.pack();

		// TODO: Make this window prettier
	}

	/**
	 * Displays the dialog window.
	 */
	public void show() {
		this.dialogFrame.pack();
		this.dialogFrame.setVisible(true);
	}

	/**
	 * A ButtonClick class to make the buttons in the confirm close dialogs
	 * actually do something
	 * 
	 * @author twtduck
	 * 
	 */
	class ButtonClick implements ActionListener {
		/**
		 * Code that runs when a button on the dialog is clicked
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(yesButton)) {
				// If the yes button is clicked, close the parent and the dialog
				// frame.
				parent.setVisible(false);
				dialogFrame.dispose();
			} else if (event.getSource().equals(noButton)) {
				// If the no button is clicked, only close the dialog
				dialogFrame.dispose();
			} else { // I hope this never happens
				Log.write(
						"A button was clicked, but can't be found in the dialog box!",
						Log.ERROR);
			}
		}
	}
}
