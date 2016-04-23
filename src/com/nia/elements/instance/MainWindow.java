package com.nia.elements.instance;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.nia.elements.global.PreparedWindow;
import com.nia.elements.instance.panels.ButtonPanel;
import com.nia.elements.instance.panels.LeftPanel;
import com.nia.elements.instance.panels.RightPanel;
import com.nia.exceptions.FullscreenNotSupportedException;

public abstract class MainWindow {
	private static PreparedWindow frame;
	private static GraphicsDevice gd;

	public static JPanel folderChooserPanel;
	public static LeftPanel leftPanel;
	public static RightPanel rightPanel;
	public static ButtonPanel buttonPanel;
	
	public static void init() throws FullscreenNotSupportedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		frame = new PreparedWindow();
				
		// Prepare for a full screen window
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if(!gd.isFullScreenSupported())
			throw new FullscreenNotSupportedException();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());;
		
		buttonPanel = new ButtonPanel();
		leftPanel = new LeftPanel();
		rightPanel = new RightPanel();
		
		frame.c.weightx = 0.37;
		frame.c.weighty = 1;
		frame.c.fill = GridBagConstraints.BOTH;
		frame.c.gridx = 0;
		

		frame.add(leftPanel);
		
		frame.c.weightx = 0.57;
		frame.c.gridx = 1;
		frame.add(rightPanel);
		
		frame.c.gridx = 2;
		frame.c.weightx = 0.06;
		frame.add(buttonPanel);

	}

	public static void display() {
		frame.pack();
		frame.setVisible(true);
		gd.setFullScreenWindow(Window.getWindows()[1]); 
	}
}

