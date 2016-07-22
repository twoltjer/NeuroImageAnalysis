package com.nia;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIMaker {

	public static void makeMainWindowPanel(JPanel contentPanel) {
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Create image directory chooser
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		

		
		JButton imageDirChooserButton = new JButton("Choose image directory");
		imageDirChooserButton.setPreferredSize(new Dimension(400,70));
		contentPanel.add(imageDirChooserButton, c);
		class ImageDirChooserButtonClick implements ActionListener {
			private JFileChooser fc;
			private int imageDirectoryChooserReturn;
			@Override
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				this.imageDirectoryChooserReturn = fc.showOpenDialog(null);
				if(this.imageDirectoryChooserReturn == 0) {
					// Folder chosen success!
					imageDirChooserButton.setText("<html>Directory chosen!<br>" + fc.getSelectedFile() + "<br>Click to change</html>");
					Main.imageDirIsSet = true;
					Main.imageDir = fc.getSelectedFile();
					Main.updateButtons();
				}
			}
			
		}
		imageDirChooserButton.addActionListener(new ImageDirChooserButtonClick());
		
		
		// Create write file location setter
		c.gridy = 1;
		JButton writeFileChooserButton = new JButton("Choose output file location");
		writeFileChooserButton.setPreferredSize(new Dimension(400,70));
		contentPanel.add(writeFileChooserButton, c);
		
	}

	public static void makeSubPanel(JPanel subPanel, MainWindow mainWin) {
		subPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(mainWin.prevButton);
		buttons.add(mainWin.scanButton);
		buttons.add(mainWin.nextButton);
		for(int x = 0; x < buttons.size(); x++) {
			c.gridx = x;
			JButton button = buttons.get(x);
			button.setPreferredSize(new Dimension(subPanel.getPreferredSize().width/3,subPanel.getPreferredSize().height));
			subPanel.add(button, c);
		}
		
		c.gridx = 0;
		c.gridwidth = 3;
		c.gridy = 1;
		JLabel pageLabel = new JLabel("Page 1 of 1"); //TODO: Make this support more than one page
		mainWin.prevButton.setEnabled(false);
		mainWin.nextButton.setEnabled(false);
		subPanel.add(pageLabel, c);
		//TODO: Brush up button sizing
		subPanel.setPreferredSize(new Dimension(500,(int) ( 80 + pageLabel.getPreferredSize().getHeight()))); 
	}

}
