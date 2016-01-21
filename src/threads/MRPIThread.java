package threads;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import logging.Log;
import system.Config;

public class MRPIThread implements Runnable {
	private String name;
	private Thread thread;

	public MRPIThread(String name) {
		this.name = name;
		logWrite("Creating thread " + name);
	}

	/**
	 * This runs as an internal method when Start() is called
	 */
	public void run() {
		logWrite("Running " + this.name);
		try {
			BufferedImage currentImage = Config.imageBeingWritten;
			JFrame frame = new JFrame("Currently processing this image");
			JLabel imgLabel = null;
			while(Config.isProcessingImages) {
				if(!currentImage.equals(Config.imageBeingWritten)) {
					currentImage = Config.imageBeingWritten;
					imgLabel = new JLabel(new ImageIcon(currentImage));
					frame.setVisible(false);
					frame.add(imgLabel);
					frame.pack();
					frame.setVisible(true);
				}
				Thread.sleep(130);
			}
		} catch (InterruptedException e) {
			logWrite("Thread " + this.name + " interrupted.");
		}
		logWrite("Thread " + this.name + " exiting.");
	}

	/**
	 * Use this to create a new thread. Then give it a ThreadTask object.
	 */
	public void start() {
		logWrite("Starting " + this.name);
		if (this.thread == null) {
			this.thread = new Thread(this, this.name);
			this.thread.start();
		}
	}

	/**
	 * Writes a debug message, to the log if it's initialized, or to the console
	 * if not
	 * 
	 * @param message
	 *            the message to write
	 */
	private void logWrite(String message) {
		if (Log.isInstantiated())
			Log.write(message, Log.DEBUG);
		else {
			System.out.println(message);
		}
	}
}
