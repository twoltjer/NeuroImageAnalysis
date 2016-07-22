package com.nia;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainWindow {
	// Essential components
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JPanel contentPanel;
	private JPanel subPanel;
	
	// Menu items
	private JMenuItem importSettingsMenuItem;
	private JMenuItem exportSettingsMenuItem;
	private JMenuItem exitMenuItem;
	private final String importSettingsMenuItemText = "Import settings";
	private final String exportSettingsMenuItemText = "Export settings";
	private final String exitMenuItemText = "Quit " + Main.PROGRAM_NAME;
	
	// Sub panel buttons
	public JButton nextButton;
	public JButton scanButton;
	public JButton prevButton;
	
	private GridBagConstraints c = new GridBagConstraints();
	public MainWindow() {
		// Create window
		this.frame = new JFrame(Main.PROGRAM_NAME);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setLayout(new GridBagLayout());
		this.c.fill = GridBagConstraints.BOTH;

		
		// Create menu bar and menu
		this.c.gridy = 0;
		this.menuBar = new JMenuBar();
		this.menu = new JMenu(Main.PROGRAM_NAME + " menu");
		this.menuBar.add(this.menu);
		
		this.importSettingsMenuItem = new JMenuItem(this.importSettingsMenuItemText);
		this.importSettingsMenuItem.setEnabled(false); //TODO: Write settings importer
		this.menu.add(this.importSettingsMenuItem);
		
		this.exportSettingsMenuItem = new JMenuItem(this.exportSettingsMenuItemText);
		this.exportSettingsMenuItem.setEnabled(false); //TODO: Write settings exporter
		this.menu.add(this.exportSettingsMenuItem);
		
		this.exitMenuItem = new JMenuItem(this.exitMenuItemText);
		this.menu.add(this.exitMenuItem);
		class ExitMenuItemClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		this.exitMenuItem.addActionListener(new ExitMenuItemClick());
		
		this.frame.add(this.menuBar, c);
		
		// Create content panel
		this.c.gridy = 1;
		this.contentPanel = new JPanel();
		GUIMaker.makeMainWindowPanel(this.contentPanel);
		this.contentPanel.setPreferredSize(new Dimension(500,300));
		this.frame.add(this.contentPanel, c);

		// Create previous, scan, and next buttons in a sub-panel
		this.c.gridy = 2;
		this.subPanel = new JPanel();
		this.subPanel.setPreferredSize(new Dimension(500,80));
		this.nextButton = new JButton(">>");
		this.scanButton = new JButton("Scan");
		this.prevButton = new JButton("<<");
		GUIMaker.makeSubPanel(this.subPanel, this);
		this.frame.add(this.subPanel, c);
		
		
	}

	public void display() {
		this.frame.pack();
		this.frame.setVisible(true);
		Main.updateButtons();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void prevButtonClick() {
		
	}
	
	public void nextButtonClick() {
		
	}
}
