package logging;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.ConfirmCloseDialog;
import gui.ScanWindow;
import system.Config;

/**
 * A really complicated class to manage a log window that automatically scrolls
 * down to the latest output. My favorite feature is the monospace font.
 * 
 * @author twtduck
 * 
 */
public abstract class LogWindowManager {
	public static boolean isInstantiated = false;

	private static JPanel panel;
	private static JFrame frame;
	private static JTextArea consoleOutputBox;
	private static JScrollPane consoleOutputBoxScrollPane;

	/**
	 * What is an abstract class without an init method? Still an abstract
	 * class, dummy!
	 * 
	 * In all seriousness, this needs to be run before trying to show the
	 * window. Otherwise, null pointer exceptions incoming!
	 */
	public static void init() {
		frame = new JFrame(Config.PROGRAM_NAME__TITLE_BAR + " Log");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		panel = new JPanel(new BorderLayout());
		consoleOutputBox = new JTextArea(22, 44);
		consoleOutputBox.setFont((new Font(Font.MONOSPACED, Font.PLAIN, 12)));
		consoleOutputBoxScrollPane = new JScrollPane(consoleOutputBox);
		consoleOutputBox.setMargin(new Insets(5, 5, 5, 5));
		consoleOutputBox.setEditable(false);
		panel.add(consoleOutputBoxScrollPane, BorderLayout.CENTER);
		frame.add(panel);

		// Ask for confirmation when closing the log window
		LogWindowManager.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ConfirmCloseDialog d = new ConfirmCloseDialog(LogWindowManager.frame,
						"You really shouldn't close this. Continue anyway?");
				d.show();
			}
		});
		LogWindowManager.isInstantiated = true;
	}

	/**
	 * Show the log window
	 */
	public static void show() {
		LogWindowManager.frame.pack();
		LogWindowManager.frame.setLocation(new Point(0, 0));
		LogWindowManager.frame.setSize(new Dimension(500, 400));
		LogWindowManager.frame.setVisible(true);
	}

	/**
	 * Hide the log window
	 */
	public static void hide() {
		LogWindowManager.frame.setVisible(false);
		if (ScanWindow.isOpen()) {

		}
	}

	/**
	 * Writes text to the log window. This is to be used only from the Log
	 * class' write() method.
	 * 
	 * @param text
	 *            text to write
	 */
	public static void write(String text) {
		LogWindowManager.consoleOutputBox.append(text + "\n");
	}

	/**
	 * Get the size of the log window to place the scan window intelligently
	 * 
	 * @return the size of the log window as a Dimension object
	 */
	public static Dimension getSize() {
		return LogWindowManager.frame.getSize();
	}

	/**
	 * Gives the Log class the ability to set the scroll bar
	 * @return the scroll pane
	 */
	public static JScrollPane getScrollpane() {
		return LogWindowManager.consoleOutputBoxScrollPane;
	}
}
