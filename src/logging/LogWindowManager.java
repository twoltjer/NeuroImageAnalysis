package logging;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.ConfirmCloseDialog;
import gui.ScanWindow;
import system.Config;

public abstract class LogWindowManager {
	public static  boolean isInstantiated = false;
	
	private static JPanel panel;
	private static JFrame frame;
	private static JTextArea consoleOutputBox;
	private static JScrollPane consoleOutputBoxScrollPane;
	
	public static void init() {
		frame = new JFrame(Config.PROGRAM_NAME__TITLE_BAR + " Log");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		panel = new JPanel(new BorderLayout());
		consoleOutputBox = new JTextArea(22,55);
		consoleOutputBoxScrollPane = new JScrollPane(consoleOutputBox);
		consoleOutputBox.setMargin(new Insets(5,5,5,5));
		consoleOutputBox.setEditable(false);
        panel.add(consoleOutputBoxScrollPane, BorderLayout.CENTER);
		frame.add(panel);
        
		// Ask for confirmation when closing the log window
		LogWindowManager.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ConfirmCloseDialog d = new ConfirmCloseDialog(LogWindowManager.frame, "You really shouldn't close this. Continue anyway?");
				d.show();
		    }
		});
		LogWindowManager.isInstantiated = true;
	}
	
	public static void show() {
		LogWindowManager.frame.pack();
		LogWindowManager.frame.setVisible(true);
	}
	
	public static void hide() {
		LogWindowManager.frame.setVisible(false);
		if(ScanWindow.isOpen()) {
			
		}
	}

	public static void write(String text) {
		LogWindowManager.consoleOutputBox.append(text + "\n");
	}
	
	public static Dimension getSize() {
		return LogWindowManager.frame.getSize();
	}
	
	public static JScrollPane getScroolpane() {
		return consoleOutputBoxScrollPane;
	}
	
}
