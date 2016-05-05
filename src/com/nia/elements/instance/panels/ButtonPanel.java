package com.nia.elements.instance.panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.nia.control.Main;
import com.nia.elements.instance.MainWindow;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 3682950170614346424L;
	public JButton importExportButton;
	public JButton previousButton;
	public JButton nextButton;
	public ButtonPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		importExportButton = new JButton("Import/Export Settings");
		importExportButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		previousButton = new JButton("Previous Panel");
		previousButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		nextButton = new JButton("Next Panel");
		nextButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add(importExportButton, c);
		c.gridy = 1;
		this.add(previousButton, c);
		c.gridy = 2;
		this.add(nextButton, c);
		c.gridy = 3;
		this.add(exitButton, c);

		class ButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(exitButton)) {
					Main.controlledStop();
				} else if(e.getSource().equals(previousButton)) {
					MainWindow.leftPanel.showPreviousPanel();
				} else if(e.getSource().equals(nextButton)) {
					MainWindow.leftPanel.showNextPanel();
				} else if(e.getSource().equals(importExportButton)) {
					MainWindow.leftPanel.showExportPanel();
				}
			}
			
		}
		
		importExportButton.addActionListener(new ButtonListener());
		previousButton.addActionListener(new ButtonListener());
		nextButton.addActionListener(new ButtonListener());
		exitButton.addActionListener(new ButtonListener());
	}
}
