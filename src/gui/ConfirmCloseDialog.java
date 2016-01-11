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

public class ConfirmCloseDialog {
	private JFrame parent;
	private JFrame dialogFrame;
	private JButton yesButton;
	private JButton noButton;
	private JLabel message;

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
	}

	public void show() {
		this.dialogFrame.pack();
		this.dialogFrame.setVisible(true);
	}
	
	class ButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(yesButton)) {
				parent.setVisible(false);
				dialogFrame.dispose();
			} else if (event.getSource().equals(noButton)) {
				dialogFrame.dispose();
			} else {
				Log.write("A button was clicked, but can't be found in the dialog box!", Log.ERROR);
			}
		}
	}
}
