package com.nia.elements.instance.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import com.nia.elements.instance.MainWindow;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int PANEL_VIEW_IMPORTEXPORT = 1;
	public static final int PANEL_VIEW_DIRECTORYCHOOSER = 2;
	private int panelView;
	private int lastPanelView;
	private ArrayList<Component> displayingComponents;
	
	public RightPanel() {
		super();
		this.setVisible(true);
		this.displayingComponents = new ArrayList<Component>();
		this.setPreferredSize(new Dimension(700, 600));
	}

	public void setPanelView(int panelViewNumber) {
		for(Component c : displayingComponents) {
			c.setVisible(false);
			this.remove(c);
		}
		while(displayingComponents.size() > 0) {
			displayingComponents.remove(0);
		}
		
		if(panelViewNumber == RightPanel.PANEL_VIEW_IMPORTEXPORT) {
			if(panelView == RightPanel.PANEL_VIEW_IMPORTEXPORT) {
				setPanelView(lastPanelView); 
			} else {
				// Import Settings label
				JLabel importLabel = new JLabel();
				importLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
				importLabel.setText("Import Settings   ");
				importLabel.setPreferredSize(new Dimension(200, 40));
				this.add(importLabel);
				this.displayingComponents.add(importLabel);
				importLabel.setVisible(true);
				
				JFileChooser fc = new JFileChooser();
				class importExportFileFilter extends FileFilter {

					@Override
					public boolean accept(File f) {
						return f.getName().endsWith(".niaconf") | f.isDirectory();
					}

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "NeuroImageAnalysis configuration file (\".niaconf\")";
					}
					
				}
				fc.setFileFilter(new importExportFileFilter());
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.add(fc);
				this.displayingComponents.add(fc);
				fc.setVisible(true);
			}
		} else if (panelViewNumber == RightPanel.PANEL_VIEW_DIRECTORYCHOOSER) {
			// Import Settings label
			JLabel chooserLabel = new JLabel();
			chooserLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			chooserLabel.setText("Scan directory:   ");
			chooserLabel.setPreferredSize(new Dimension(200, 40));
			this.add(chooserLabel);
			this.displayingComponents.add(chooserLabel);
			chooserLabel.setVisible(true);
			
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			this.add(fc);
			this.displayingComponents.add(fc);
			fc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			Component buttonsPanel = fc.getComponent(2); // Hopefully this remains this way
			fc.remove(buttonsPanel);
			fc.setVisible(true);
		}
		
		MainWindow.repack();
		lastPanelView = panelView;
		panelView = panelViewNumber;
	}
}
