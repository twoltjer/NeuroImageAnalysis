// Start com/nia/elements/instance/panels/LeftPanel.java

package com.nia.elements.instance.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nia.elements.instance.MainWindow;

public class LeftPanel extends JPanel {
	private static final long serialVersionUID = 4091651961437129788L;

	private int selectedPanelIndex;
	private ArrayList<JPanel> panels;

	public class FolderChooserPanelLeft extends JPanel implements
			RightPanelDisplayable {
		private static final long serialVersionUID = 1372667393069927345L;

		public FolderChooserPanelLeft() {
			super();
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;

			// Create JLabel for text
			JLabel label = new JLabel("Image Directory");
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			
			label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			c.insets = new Insets(0, this.getWidth() / 2, 0,
					this.getWidth() / 2);
			this.add(new JPanel(), c);
			this.add(label, c);

		}

		public void display() {
			this.setBorder(null);
			MainWindow.rightPanel.setPanelView(RightPanel.PANEL_VIEW_DIRECTORYCHOOSER);
		}
	}

	public class OptionsPanelLeft extends JPanel implements
			RightPanelDisplayable {
		private static final long serialVersionUID = 1372667393069927345L;

		public OptionsPanelLeft() {
			super();
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;

			c.weightx = 1;
			c.weighty = 1;

			// Create JLabel for text
			JLabel label = new JLabel("Options");
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			label.setBackground(Color.CYAN);
			label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			c.insets = new Insets(0, this.getWidth() / 2, 0,
					this.getWidth() / 2);
			this.add(new JPanel(), c);
			this.add(label, c);
		}

		@Override
		public void display() {
			this.setBorder(null);
		}
	}

	public class ColorsPanelLeft extends JPanel implements
			RightPanelDisplayable {
		private static final long serialVersionUID = 1372667393069927345L;

		public ColorsPanelLeft() {
			super();
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;

			// Create JLabel for text
			JLabel label = new JLabel("Colors");
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			label.setBackground(Color.CYAN);
			label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			c.insets = new Insets(0, this.getWidth() / 2, 0,
					this.getWidth() / 2);
			this.add(new JPanel(), c);
			this.add(label, c);
		}

		@Override
		public void display() {
			this.setBorder(null);
		}
	}

	public class ScanPanelLeft extends JPanel implements RightPanelDisplayable {
		private static final long serialVersionUID = 1372667393069927345L;

		public ScanPanelLeft() {
			super();
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;

			// Create JLabel for text
			JLabel label = new JLabel("Scan");
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			label.setBackground(Color.CYAN);
			label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			c.insets = new Insets(0, this.getWidth() / 2, 0,
					this.getWidth() / 2);
			this.add(new JPanel(), c);
			this.add(label, c);
		}

		@Override
		public void display() {
			this.setBorder(null);
		}
	}

	public LeftPanel() {
		super();
		panels = new ArrayList<JPanel>();
		panels.add(new FolderChooserPanelLeft());
		panels.add(new OptionsPanelLeft());
		panels.add(new ColorsPanelLeft());
		panels.add(new ScanPanelLeft());

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		for (int i = 0; i < panels.size(); i++) {
			c.gridy = i;
			this.add(panels.get(i), c);
		}
		setSelectedPanel(0);
		this.setPreferredSize(new Dimension(200, 600));
	}

	public void showNextPanel() {
		this.setSelectedPanel(selectedPanelIndex + 1);
	}

	public void showPreviousPanel() {
		this.setSelectedPanel(selectedPanelIndex - 1);
	}

	public void showExportPanel() {
		this.setSelectedPanel(-1);
	}

	private void setSelectedPanel(int index) {
		// Set borders
		for (JPanel panel : panels) {
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		if (index != -1) {
			// Display the new right panel and remove the left panel's border
			JPanel jp = this.panels.get(index);
			if (index == 0) {
				((FolderChooserPanelLeft) jp).display();
			} else if (index == 1) {
				((OptionsPanelLeft) jp).display();
			} else if (index == 2) {
				((ColorsPanelLeft) jp).display();
			} else if (index == 3) {
				((ScanPanelLeft) jp).display();
			}

			// check the panels next to it
			if (index > 0) {
				panels.get(index - 1).setBorder(
						BorderFactory.createLineBorder(Color.BLACK));
				MainWindow.buttonPanel.previousButton.setEnabled(true);
			} else {
				MainWindow.buttonPanel.previousButton.setEnabled(false);
			}

			if (index < panels.size() - 1) {
				panels.get(index + 1).setBorder(
						BorderFactory.createLineBorder(Color.BLACK));
				MainWindow.buttonPanel.nextButton.setEnabled(true);

			} else {
				MainWindow.buttonPanel.nextButton.setEnabled(false);
			}
			this.selectedPanelIndex = index;

			// If the import/export button isn't in use, make its text black
			MainWindow.buttonPanel.importExportButton.setForeground(Color.BLACK);

		} else {
			MainWindow.buttonPanel.nextButton.setEnabled(false);
			MainWindow.buttonPanel.previousButton.setEnabled(false);
			MainWindow.buttonPanel.importExportButton.setForeground(Color.BLUE);
			MainWindow.rightPanel.setPanelView(RightPanel.PANEL_VIEW_IMPORTEXPORT);
			if (this.selectedPanelIndex == index) {
				this.setSelectedPanel(index + 1);
			} else {
				this.selectedPanelIndex = index;
			}
		}
	}
}

// End com/nia/elements/instance/panels/LeftPanel.java