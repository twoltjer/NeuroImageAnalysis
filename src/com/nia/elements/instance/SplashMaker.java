package com.nia.elements.instance;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.nia.elements.global.Log;

public class SplashMaker implements Runnable {
	private JFrame splashFrame;
	
	@Override
	public void run() {
		Log.println("Starting splash");
		splashFrame = new JFrame("NIA Launcher");
		splashFrame.setUndecorated(true);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon(getClass().getResource("nia.png")));
		splashFrame.getContentPane().add(imageLabel);
		splashFrame.pack();
		splashFrame.setLocationRelativeTo(null);
		splashFrame.setVisible(true);
		Log.println("Splash displaying");
	}
	
	public void splashDown() {
		Log.println("Splash closing");
		splashFrame.dispose();
	}
}
