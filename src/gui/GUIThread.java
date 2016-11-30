// Begin gui/GUIThread.java

package gui;

import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import global.Config;
import global.DebugMessenger;
import global.RuntimeConfig;

/**
 * Thread that starts the GUI, and contains and runs GUI methods.
 * 
 * @author Thomas Woltjer
 */
	public class GUIThread extends Thread {
		public static final int CHOOSER_HUB = 0;
		public static final int LAUNCH_IMAGE_PREVIEWER = 1;
		public static final int MEMORY_USAGE_WINDOW = 2;
		public static final int PREVIEWER_FOCUS = 3;
		public static final int PREVIEWER_BUTTON_UPDATE = 4;
		private int guiNumber;
		
		// ==========================================================================
		// |                               START CODE                               |
		// ==========================================================================
		
		/**
		 * Runs begins the thread. This method is also for pre-run configuration,
		 * but so far there is none of that.
		 * 
		 * @param GUINumber
		 *            The GUI number, indicating which GUI to start.
		 */
		public void startThread(int GUINumber) {
			DebugMessenger.out("Starting new thread for GUI");
			guiNumber = GUINumber;
			start();
		}
	
		/**
		 * Starts the thread, and runs the createChooserHub() and assignChooserHub()
		 * methods.
		 */
		@Override
		public void run() {
			// Create the chooser, and the rest should run from there
			DebugMessenger.out("Thread running");
			if (guiNumber == GUIThread.CHOOSER_HUB)
				createChooserHub();
			if (guiNumber == GUIThread.LAUNCH_IMAGE_PREVIEWER)
				createPreviewer();
			if (guiNumber == GUIThread.MEMORY_USAGE_WINDOW)
				createMemoryUsageWindow();
			if (guiNumber == GUIThread.PREVIEWER_FOCUS)
				runPreviewerFocusLoop();
			if (guiNumber == GUIThread.PREVIEWER_BUTTON_UPDATE)
				updatePreviewerButtons();
		}
	
	// ==========================================================================
	// |                            CHOOSER HUB CODE                            |
	// ==========================================================================
	
	/**
	 * Creates hub window for choosing image directory and output file
	 */
	private void createChooserHub() {
		DebugMessenger.out("Creating chooser hub");
		instantiateChooserHubObjects();
		setUpChooserHubGUI();
		RuntimeConfig.chooserHubExists = true;
		assignChooserHubButtons();
	}

	/**
	 * Removes the chooser hub from view, then memory.
	 */
	public void destroyChooserHub() {
		DebugMessenger.out("Destroying chooser hub");
		if(RuntimeConfig.chooserHubExists) {
			GUIObjects.ChooserObjects.chooserFrame.setVisible(false);
			GUIObjects.ChooserObjects.chooserFrame.dispose();
			RuntimeConfig.chooserHubExists = false;
			DebugMessenger.out("Chooser hub destroyed");
		} else {
			DebugMessenger.out("Chooser hub doesn't exist, so skipping destruction.");
		}
	}

	/**
	 * Instantiates chooser hub gui objects
	 */
	private void instantiateChooserHubObjects() {
		DebugMessenger.out("Creating chooser hub objects");
		GUIObjects.ChooserObjects.chooserFrame = new JFrame(Config.PROGRAM_NAME);
		GUIObjects.ChooserObjects.imageDirChooserButton = new JButton(RuntimeConfig.imageDirButtonText);
		GUIObjects.ChooserObjects.outputFileDirChooserButton = new JButton(RuntimeConfig.outputFileDirButtonText);
		GUIObjects.ChooserObjects.outputFileNameTextAreaLabel = new JLabel(Config.CHOOSER_HUB_OUTPUT_FILE_NAME_TEXTAREA_LABEL_TEXT);
		GUIObjects.ChooserObjects.chooserHubScanButton = new JButton(Config.CHOOSER_HUB_SCAN_BUTTON_TEXT);
		GUIObjects.ChooserObjects.outputFileNameTextArea = new JTextArea(Config.CHOOSER_HUB_OUTPUT_FILE_NAME_TEXTAREA_TEXT);
		GUIObjects.ChooserObjects.chooserHubMainPanel = new JPanel();
		DebugMessenger.out("Done creating chooser hub objects");
	}

	/**
	 * Sets up the user interface, using a GridBagLayout
	 */
	private void setUpChooserHubGUI() {
		DebugMessenger.out("Setting up chooser hub");
		GUIObjects.ChooserObjects.chooserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUIObjects.ChooserObjects.chooserHubMainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 12;
		c.ipady = 8;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		// is left, is right, is top, is not bot
		c.insets = createInsets(true, true, true, false);
		GUIObjects.ChooserObjects.imageDirChooserButton.setPreferredSize(Config.CHOOSER_HUB_BUTTON_SIZE);
		GUIObjects.ChooserObjects.chooserHubMainPanel.add(GUIObjects.ChooserObjects.imageDirChooserButton, c);
		c.gridy = 1;
		// is left, is right, is not top, is not bot
		c.insets = createInsets(true, true, false, false);
		GUIObjects.ChooserObjects.outputFileDirChooserButton.setPreferredSize(Config.CHOOSER_HUB_BUTTON_SIZE);
		GUIObjects.ChooserObjects.chooserHubMainPanel.add(GUIObjects.ChooserObjects.outputFileDirChooserButton, c);
		c.gridy = 2;
		c.gridwidth = 1;
		c.ipady = 0;
		// is left, is not right, is not top (but we want border anyway), is bot
		c.insets = createInsets(true, false, true, true);
		GUIObjects.ChooserObjects.chooserHubMainPanel.add(GUIObjects.ChooserObjects.outputFileNameTextAreaLabel, c);
		c.gridy = 2;
		c.gridx = 1;
		// is not left, is right, is not top (but we want border anyway), is bot
		c.insets = createInsets(false, true, true, true);
		GUIObjects.ChooserObjects.chooserHubMainPanel.add(GUIObjects.ChooserObjects.outputFileNameTextArea, c);
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridx = 0;
		c.ipady = 8;
		// is left, is right, is not top, is bot
		c.insets = createInsets(true, true, false, true);
		GUIObjects.ChooserObjects.chooserHubScanButton.setPreferredSize(Config.CHOOSER_HUB_BUTTON_SIZE);
		GUIObjects.ChooserObjects.chooserHubMainPanel.add(GUIObjects.ChooserObjects.chooserHubScanButton, c);
		GUIObjects.ChooserObjects.chooserFrame.add(GUIObjects.ChooserObjects.chooserHubMainPanel);
		GUIObjects.ChooserObjects.chooserFrame.pack();
		DebugMessenger.out("A quick refresh before showing the chooser hub");
		RuntimeConfig.refreshChooserVars();
		centerWindow(GUIObjects.ChooserObjects.chooserFrame);
		GUIObjects.ChooserObjects.chooserFrame.setVisible(true);
		DebugMessenger.out("Finished creating chooser window");
	}

	/**
	 * Assign actionlistener objects to chooser buttons
	 */
	private void assignChooserHubButtons() {
		DebugMessenger.out("Assigning action listeners to chooser buttons");
		GUIObjects.ChooserObjects.imageDirChooserButton.addActionListener(new ButtonClick());
		GUIObjects.ChooserObjects.outputFileDirChooserButton.addActionListener(new ButtonClick());
		GUIObjects.ChooserObjects.chooserHubScanButton.addActionListener(new ButtonClick());
		DebugMessenger.out("Done assigning action listeners to chooser buttons");
	}
	// ==========================================================================
	// |                             PREVIEWER CODE                             |
	// ==========================================================================
	
	/**
	 * Creates previewer for tuning data collection settings before running
	 */
	private void createPreviewer() {
		DebugMessenger.out("Creating image previewer");
		// This buffer happens as fast as possible
		BufferManager.initialBuffer();
		
		setDefaultPreviewerVars();
		instantiatePreviewerObjects();
		setUpPreviewerGUI();
		destroyChooserHub();
		assignKeyShortcuts();
		assignPreviewerButtons();
		
		DebugMessenger.out("Done with quick setup. Now doing long buffer");
		// Completely buffer
		BufferManager.buffer();
	}
	
	/**
	 * Removes the chooser hub from view, then memory.
	 */
	public void destroyPreviewer() {
		DebugMessenger.out("Destroying previewer");
		GUIObjects.PreviewerObjects.previewFrame.setVisible(false);
		GUIObjects.PreviewerObjects.previewFrame.dispose();
		DebugMessenger.out("Previewer destroyed");
	}
	
	private void setDefaultPreviewerVars() {
		RuntimeConfig.threshold = RuntimeConfig.previewerDisplayImage.thresh;
		RuntimeConfig.isBuffering = true;
	}
	
	private void instantiatePreviewerObjects() {
		DebugMessenger.out("Creating previewer objects");
		GUIObjects.PreviewerObjects.analyzeButton = new JButton(Config.PREVIEWER_ANALYZE_BUTTON_TEXT);
		GUIObjects.PreviewerObjects.bufferProgress = new JProgressBar();
		GUIObjects.PreviewerObjects.cancelButton = new JButton(Config.PREVIEWER_CANCEL_BUTTON_TEXT);
		GUIObjects.PreviewerObjects.centerButtonPanel = new JPanel();
		GUIObjects.PreviewerObjects.decLgButton = new JButton(Config.PREVIEWER_DEC_PREFIX + Config.THRESH_CHANGE_LG_AMOUNT);
		GUIObjects.PreviewerObjects.decSmButton = new JButton(Config.PREVIEWER_DEC_PREFIX + Config.THRESH_CHANGE_SM_AMOUNT);
		GUIObjects.PreviewerObjects.imagePanel = new JPanel();
		GUIObjects.PreviewerObjects.incLgButton = new JButton(Config.PREVIEWER_INC_PREFIX + Config.THRESH_CHANGE_LG_AMOUNT);
		GUIObjects.PreviewerObjects.incSmButton = new JButton(Config.PREVIEWER_INC_PREFIX + Config.THRESH_CHANGE_SM_AMOUNT);
		GUIObjects.PreviewerObjects.leftButtonPanel = new JPanel();
		GUIObjects.PreviewerObjects.nextDMButton = new JButton(Config.PREVIEWER_DM_NAMES[RuntimeConfig.getNextDMNumber()]);
		GUIObjects.PreviewerObjects.nextImgButton = new JButton(Config.PREVIEWER_NEXT_BUTTON_TEXT);
		GUIObjects.PreviewerObjects.prevDMButton = new JButton(Config.PREVIEWER_DM_NAMES[RuntimeConfig.getPrevDMNumber()]);
		GUIObjects.PreviewerObjects.previewFrame = new JFrame(Config.PROGRAM_NAME);
		GUIObjects.PreviewerObjects.prevImgButton = new JButton(Config.PREVIEWER_PREV_BUTTON_TEXT);
		GUIObjects.PreviewerObjects.rightButtonPanel = new JPanel();
		GUIObjects.PreviewerObjects.statusLabel = new JLabel("Buffering...");
		GUIObjects.PreviewerObjects.threshBotLabel = new JLabel(Integer.toString(RuntimeConfig.threshold));
		GUIObjects.PreviewerObjects.threshDisplayPanel = new JPanel();
		GUIObjects.PreviewerObjects.threshTopLabel = new JLabel(Config.PREVIEWER_THRESH_LABEL_TOP_TEXT);
		DebugMessenger.out("Done creating previewer objects");
	}
	
	private void setUpPreviewerGUI() {
		DebugMessenger.out("Setting up previewer");
		GUIObjects.PreviewerObjects.previewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = GUIObjects.PreviewerObjects.previewFrame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		// is left, is not right, is top, is not bot
		c.insets = createInsets(true, false, true, false);
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = Config.GUI_INTERNAL_PADDING_X;
		c.ipady = Config.GUI_INTERNAL_PADDING_Y;
		setUpPreviewerTopLeftPanel();
		pane.add(GUIObjects.PreviewerObjects.leftButtonPanel, c);
		c.gridx = 1;
		// is not left, is not right, is top, is not bot
		c.insets = createInsets(false, false, true, false);
		setUpPreviewerTopCenterPanel();
		pane.add(GUIObjects.PreviewerObjects.imagePanel, c);
		c.gridx = 2;
		// is not left, is right, is top, is not bot
		c.insets = createInsets(false, true, true, false);
		setUpPreviewerTopRightPanel();
		pane.add(GUIObjects.PreviewerObjects.rightButtonPanel, c);
		c.gridy = 1;
		c.gridx = 0;
		// is left, is not right, is not top, is not bot
		c.insets = createInsets(true, false, false, false);
		c.fill = GridBagConstraints.NONE;
		setUpPreviewerCenterLeftPanel();
		pane.add(GUIObjects.PreviewerObjects.cancelButton, c);
		c.gridx = 1;
		// is not left, is not right, is not top, is not bot
		c.insets = createInsets(false, false, false, false);
		c.fill = GridBagConstraints.BOTH;
		setUpPreviewerCenterCenterPanel();
		pane.add(GUIObjects.PreviewerObjects.centerButtonPanel, c);
		c.gridx = 2;
		// is not left, is right, is not top, is not bot
		c.insets = createInsets(false, true, false, false);
		c.fill = GridBagConstraints.NONE;
		setUpPreviewerCenterRightPanel();
		pane.add(GUIObjects.PreviewerObjects.analyzeButton, c);
		c.gridy = 2;
		c.gridx = 0;
		// is left, is not right, is not top, is bot
		c.insets = createInsets(true, false , false, true);
		GUIObjects.PreviewerObjects.statusLabel.setPreferredSize(Config.PREVIEWER_LARGE_BUTTON_SIZE);
		pane.add(GUIObjects.PreviewerObjects.statusLabel, c);
		// The progress bar has a specific size that has been measured out without the help of internal padding or filling
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 1;
		// is not left, is not right, is not top, is bot
		c.insets = createInsets(false, false, false, true);
		GUIObjects.PreviewerObjects.bufferProgress.setPreferredSize(Config.PREVIEWER_PROG_BAR_SIZE);
		GUIObjects.PreviewerObjects.bufferProgress.setStringPainted(true);
		GUIObjects.PreviewerObjects.bufferProgress.setString(GUIObjects.PreviewerObjects.statusLabel.getText());
		
		pane.add(GUIObjects.PreviewerObjects.bufferProgress, c);
		GUIObjects.PreviewerObjects.previewFrame.pack();
		GUIObjects.PreviewerObjects.previewFrame.setVisible(true);
		centerWindow(GUIObjects.PreviewerObjects.previewFrame);
	}
	
	private void setUpPreviewerTopLeftPanel() {
		DebugMessenger.out("Setting up previewer top left panel");
		GUIObjects.PreviewerObjects.leftButtonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = Config.GUI_INTERNAL_PADDING_X;
		c.ipady = Config.GUI_INTERNAL_PADDING_Y;
		GUIObjects.PreviewerObjects.prevImgButton.setPreferredSize(Config.PREVIEWER_LARGE_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.leftButtonPanel.add(GUIObjects.PreviewerObjects.prevImgButton, c);
		c.gridy = 1;
		GUIObjects.PreviewerObjects.prevDMButton.setPreferredSize(Config.PREVIEWER_LARGE_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.leftButtonPanel.add(GUIObjects.PreviewerObjects.prevDMButton, c);
		DebugMessenger.out("Done setting up top left panel");
	}

	private void setUpPreviewerTopCenterPanel() {
		DebugMessenger.out("Setting up previewer top center panel. This is the image panel");
		GUIObjects.PreviewerObjects.imagePanel.add(new JLabel(new ImageIcon(RuntimeConfig.previewerDisplayImage.image)));
		DebugMessenger.out("Done setting up previewer top center panel");
	}
	private void setUpPreviewerTopRightPanel() {
		DebugMessenger.out("Setting up previewer top right panel");
		GUIObjects.PreviewerObjects.rightButtonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = Config.GUI_INTERNAL_PADDING_X;
		c.ipady = Config.GUI_INTERNAL_PADDING_Y;
		GUIObjects.PreviewerObjects.nextImgButton.setPreferredSize(Config.PREVIEWER_LARGE_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.rightButtonPanel.add(GUIObjects.PreviewerObjects.nextImgButton, c);
		c.gridy = 1;
		GUIObjects.PreviewerObjects.nextDMButton.setPreferredSize(Config.PREVIEWER_LARGE_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.rightButtonPanel.add(GUIObjects.PreviewerObjects.nextDMButton, c);
		DebugMessenger.out("Done setting up top right panel");
	}

	private void setUpPreviewerCenterLeftPanel() {
		DebugMessenger.out("Setting up previewer center left panel");
		GUIObjects.PreviewerObjects.cancelButton.setPreferredSize(Config.PREVIEWER_LARGE_BUTTON_SIZE);
		DebugMessenger.out("Done setting up center left panel");
	}
	
	private void setUpPreviewerCenterCenterPanel() {
		DebugMessenger.out("Setting up previewer center center panel");
		GUIObjects.PreviewerObjects.centerButtonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = Config.GUI_INTERNAL_PADDING_X;
		c.ipady = Config.GUI_INTERNAL_PADDING_Y;
		c.gridx = 0;
		c.gridy = 0;
		GUIObjects.PreviewerObjects.decLgButton.setPreferredSize(Config.PREVIEWER_SMALL_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.centerButtonPanel.add(GUIObjects.PreviewerObjects.decLgButton, c);
		c.gridx = 1;
		GUIObjects.PreviewerObjects.decSmButton.setPreferredSize(Config.PREVIEWER_SMALL_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.centerButtonPanel.add(GUIObjects.PreviewerObjects.decSmButton, c);
		c.gridx = 2;
		setUpPreviewerThreshLabelPanel();
		GUIObjects.PreviewerObjects.centerButtonPanel.add(GUIObjects.PreviewerObjects.threshDisplayPanel, c);
		c.gridx = 3;
		GUIObjects.PreviewerObjects.incSmButton.setPreferredSize(Config.PREVIEWER_SMALL_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.centerButtonPanel.add(GUIObjects.PreviewerObjects.incSmButton, c);
		c.gridx = 4;
		GUIObjects.PreviewerObjects.incLgButton.setPreferredSize(Config.PREVIEWER_SMALL_BUTTON_SIZE);
		GUIObjects.PreviewerObjects.centerButtonPanel.add(GUIObjects.PreviewerObjects.incLgButton, c);
		DebugMessenger.out("Done setting up center center panel");
	}
	
	private void setUpPreviewerThreshLabelPanel() {
		DebugMessenger.out("Setting up previewer thresh labelpanel");
		GUIObjects.PreviewerObjects.threshDisplayPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 0;
		GUIObjects.PreviewerObjects.threshDisplayPanel.add(GUIObjects.PreviewerObjects.threshTopLabel, c);
		c.gridy = 1;
		GUIObjects.PreviewerObjects.threshDisplayPanel.add(GUIObjects.PreviewerObjects.threshBotLabel, c);
		GUIObjects.PreviewerObjects.threshDisplayPanel.setPreferredSize(Config.PREVIEWER_SMALL_BUTTON_SIZE);
		DebugMessenger.out("Done setting up thresh label panel");
	}
	
	private void setUpPreviewerCenterRightPanel() {
		DebugMessenger.out("Setting up previewer center right panel");
		GUIObjects.PreviewerObjects.analyzeButton.setPreferredSize(Config.PREVIEWER_LARGE_BUTTON_SIZE);
		DebugMessenger.out("Done setting up center right panel");
	}

	private void assignKeyShortcuts() {
		DebugMessenger.out("Requesting focus for the preview frame.");
		GUIObjects.PreviewerObjects.previewFrame.requestFocus();
		class CloseListener implements KeyListener {
			public ArrayList<Integer> keysPressed = new ArrayList<Integer>(); //Stored as key codes
			@Override
			public void keyPressed(KeyEvent arg0) {
				DebugMessenger.out("KEY PRESSED");
				int kc = arg0.getKeyCode();
				keysPressed.add(new Integer(kc));
				if(keysPressed.contains(new Integer(17))) {
					if(keysPressed.contains(new Integer(88))) {
						DebugMessenger.out("Keyboard shortcut for closing triggered. Exiting now.");
						System.exit(0);
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				DebugMessenger.out("KEY RELEASED");
				keysPressed.remove(new Integer(arg0.getKeyCode()));
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// Empty method
			}
			
		}
		DebugMessenger.out("Assigning keyboard shortcuts.");
		GUIObjects.PreviewerObjects.previewFrame.addKeyListener(new CloseListener());
		DebugMessenger.out("Requesting start of preview focus thread");
		GUIThread focusThread = new GUIThread();
		focusThread.startThread(GUIThread.PREVIEWER_FOCUS);
	}

	private void runPreviewerFocusLoop() {
		while(true) {
			
			if(GUIObjects.PreviewerObjects.previewFrame.isActive() && !GUIObjects.PreviewerObjects.previewFrame.hasFocus()) {
				DebugMessenger.out("Resetting focus to previewer frame");
				GUIObjects.PreviewerObjects.previewFrame.requestFocus();
			} else {
				// Uncomment the below line to spam the debug output with messages saying everything is okay
				//DebugMessenger.out("Previewer frame still has focus. Doing nothing.");
			}
			try {
				Thread.sleep(Config.THREAD_LOOP_WAIT_TIME_MILLIS);
			} catch (InterruptedException e) {
				System.err.println("Previewer focus loop thread failed to sleep. Must exit.");
				System.exit(-1);
			}
		}
	}
	private void assignPreviewerButtons() {
		DebugMessenger.out("Setting action listeners for Previewer buttons");
		JButton[] buttons = getPreviewerButtons();
		for(JButton jb : buttons) {
			jb.addActionListener(new ButtonClick());
		}
		DebugMessenger.out("Done setting action listeners for Previewer buttons");
	}
	
	
	public void disablePreviewerButtons() {
		DebugMessenger.out("Disabling previewer buttons");
		JButton[] buttons = getPreviewerButtons();
		for(JButton jb : buttons) {
			if(jb.isEnabled()) 
				RuntimeConfig.tempDisabledPreviewerButtons.add(jb);
			jb.setEnabled(false);
		}
		DebugMessenger.out("Done disabling previewer buttons");
	}
	
	public JButton[] getPreviewerButtons() {
		DebugMessenger.out("Getting list of previewer buttons");
		JButton[] buttons = { GUIObjects.PreviewerObjects.prevDMButton, 
				GUIObjects.PreviewerObjects.prevImgButton,
				GUIObjects.PreviewerObjects.nextImgButton, 
				GUIObjects.PreviewerObjects.nextDMButton, 
				GUIObjects.PreviewerObjects.cancelButton, 
				GUIObjects.PreviewerObjects.decLgButton, 
				GUIObjects.PreviewerObjects.decSmButton, 
				GUIObjects.PreviewerObjects.incLgButton, 
				GUIObjects.PreviewerObjects.incSmButton, 
				GUIObjects.PreviewerObjects.analyzeButton };
		return buttons;
	}

	public static void updateBufferProgBar() {
		// Maximum is set by BufferManager.buffer() method
		int value = RuntimeConfig.bufferedImages.size();
		GUIObjects.PreviewerObjects.bufferProgress.setValue(value);

		GUIObjects.PreviewerObjects.bufferProgress.setString("Buffering " + value + "/" + GUIObjects.PreviewerObjects.bufferProgress.getMaximum());
		GUIObjects.PreviewerObjects.bufferProgress.setStringPainted(true);
		if(value == GUIObjects.PreviewerObjects.bufferProgress.getMaximum()) {
			RuntimeConfig.isBuffering = false;
			GUIObjects.PreviewerObjects.statusLabel.setText("Buffering complete");
			for(JButton jb : RuntimeConfig.tempDisabledPreviewerButtons) {
				jb.setEnabled(true);
			}
			RuntimeConfig.tempDisabledPreviewerButtons.clear();
		}
	}
	
	public static void updatePreviewerDispImage() {
		GUIObjects.PreviewerObjects.previewFrame.setVisible(false);
		GUIObjects.PreviewerObjects.imagePanel.removeAll();
		GUIObjects.PreviewerObjects.imagePanel.add(new JLabel(new ImageIcon(RuntimeConfig.previewerDisplayImage.image)));
		GUIObjects.PreviewerObjects.previewFrame.pack();
		GUIObjects.PreviewerObjects.previewFrame.setVisible(true);
	}

	public static void updatePreviewerButtons() {
		DebugMessenger.out("Setting buttons");
		GUIObjects.PreviewerObjects.decLgButton.setEnabled(BufferManager.BUFFER_LG_DEC);
		GUIObjects.PreviewerObjects.decSmButton.setEnabled(BufferManager.BUFFER_SM_DEC);
		GUIObjects.PreviewerObjects.incLgButton.setEnabled(BufferManager.BUFFER_LG_INC);
		GUIObjects.PreviewerObjects.incSmButton.setEnabled(BufferManager.BUFFER_SM_INC);
		GUIObjects.PreviewerObjects.nextImgButton.setEnabled(BufferManager.BUFFER_NEXT);
		GUIObjects.PreviewerObjects.prevImgButton.setEnabled(BufferManager.BUFFER_PREV);
		GUIObjects.PreviewerObjects.prevDMButton.setText(Config.PREVIEWER_DM_NAMES[RuntimeConfig.getPrevDMNumber()]);
		GUIObjects.PreviewerObjects.nextDMButton.setText(Config.PREVIEWER_DM_NAMES[RuntimeConfig.getNextDMNumber()]);
		DebugMessenger.out("Previous image button is enabled: " + BufferManager.BUFFER_PREV);
		GUIObjects.PreviewerObjects.threshBotLabel.setText(Integer.toString(RuntimeConfig.previewerDisplayImage.thresh));
		DebugMessenger.out("Done setting buttons");
	}
	
	// ==========================================================================
	// |                              MEMORY USAGE                              |
	// ==========================================================================

	public static void createMemoryUsageWindow() {
		DebugMessenger.out("Starting mem window");
		JFrame frame = new JFrame(Config.PROGRAM_NAME);
		JProgressBar memBar = new JProgressBar();
		memBar.setPreferredSize(Config.PREVIEWER_PROG_BAR_SIZE);
		memBar.setMaximum(300);
		frame.add(memBar);
		frame.pack();
		frame.setVisible(true);	

		while(true) {
			try {
				int used = (int) Runtime.getRuntime().totalMemory() / 1024 / 1024;
				int total = (int) Runtime.getRuntime().maxMemory() / 1024 / 1024;
				Thread.sleep(100);
				String s = used + "M / " + total + "M";
				memBar.setMaximum(total);
				memBar.setValue(used);
				memBar.setToolTipText(s);
				memBar.setString(s);
				memBar.setStringPainted(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ==========================================================================
	// |                              GENERAL CODE                              |
	// ==========================================================================

	/**
	 * Creates an Insets object for providing border from the edge of the
	 * window. Can also be used to create space between objects inside a window.
	 * <p>
	 * <b><i>The amount of space is set by Config.BORDER_WIDTH</i></b>
	 * 
	 * @param isLeftEdge
	 *            If the object needs space on the left
	 * @param isRightEdge
	 *            If the object needs space on the right
	 * @param isTopEdge
	 *            If the object needs space above
	 * @param isBotEdge
	 *            If the object needs space below
	 * @return An Insets object that has been created.
	 */
	private Insets createInsets(boolean isLeftEdge, boolean isRightEdge, boolean isTopEdge, boolean isBotEdge) {
		DebugMessenger.outNoNewLine("Creating insets: Borders on the following: ");
		int topBorder = 0;
		int leftBorder = 0;
		int botBorder = 0;
		int rightBorder = 0;
		if (isTopEdge) {
			topBorder = Config.GUI_BORDER_WIDTH;
			DebugMessenger.outNoNewLine("top ");
		}
		if (isLeftEdge) {
			leftBorder = Config.GUI_BORDER_WIDTH;
			DebugMessenger.outNoNewLine("left ");
		}
		if (isBotEdge) {
			botBorder = Config.GUI_BORDER_WIDTH;
			DebugMessenger.outNoNewLine("bot ");
		}
		if (isRightEdge) {
			rightBorder = Config.GUI_BORDER_WIDTH;
			DebugMessenger.outNoNewLine("right ");
		}
		DebugMessenger.out("");
		return new Insets(topBorder, leftBorder, botBorder, rightBorder);
	}

	/**
	 * Moves a window to the center of the screen.
	 * @param window A JFrame window, which should have had pack() run on it.
	 */
	private void centerWindow(JFrame window) {
		DebugMessenger.out("Centering window");
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		window.setLocation((int) ((width - window.getSize().getWidth()) / 2),
				(int) ((height - window.getSize().getHeight()) / 2));
	}
}

// End gui/GUIThread.java
