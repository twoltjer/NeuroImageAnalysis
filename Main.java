import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	static File jarFile = null; 
	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("NIA Patcher v1.0");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton updateButton = new JButton("Checking for updates...");
		JButton launchButton = new JButton("Start NIA");
		c.ipadx = 40;
		c.ipady = 30;
		c.insets = new Insets(55, 55, 10, 55);
		c.gridy = 0;
		window.add(updateButton, c);
		c.insets = new Insets(10, 55, 55, 55);
		c.gridy = 1;
		window.add(launchButton, c);
		window.pack();
		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		window.setLocation((dm.getWidth() / 2) - (window.getWidth() / 2),
				(dm.getHeight() / 2) - (window.getHeight() / 2));
		window.setVisible(true);
		boolean isUpToDate = false;
		boolean localVersionExists = false;
		boolean internetConnected = true;
		updateButton.setEnabled(false);
		launchButton.setEnabled(false);
		String workDir;
		if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
			workDir = System.getenv("AppData");
		} else {
			workDir = System.getProperty("user.home") + "/.config/share";
		}
		File appDataDir = new File(workDir);
		File niaDataDir = new File(appDataDir, "NIA");
		File latestVersionFile = new File(niaDataDir, "latestVersion");
		jarFile = new File(niaDataDir, "NIA.jar");;
		if(latestVersionFile.exists())
			latestVersionFile.delete();
		try {
			if (!niaDataDir.exists()) {
				niaDataDir.mkdirs();
			}
			latestVersionFile.createNewFile();
			URL website = new URL("https://raw.githubusercontent.com/twtduck/NeuroImageAnalysis/updates/VERSION");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(latestVersionFile);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (IOException e) {
			internetConnected = false;
			updateButton.setText("No connection to server");
			//System.exit(-1);
		}
		File currentVersionFile = new File(niaDataDir, "currentVersion");
		String currentVersion = null;
		if (currentVersionFile.exists()) {
			// Read version from version file
			Scanner currentVersionFileReader = new Scanner(currentVersionFile);
			currentVersion = currentVersionFileReader.nextLine();
			currentVersionFileReader.close();
			localVersionExists = true;
		}
		String latestVersion = null;
		Scanner latestVersionFileReader = new Scanner(latestVersionFile);
		if (internetConnected)
			latestVersion = latestVersionFileReader.nextLine();
		latestVersionFileReader.close();
		try {
			isUpToDate = currentVersion.equals(latestVersion);
		} catch (NullPointerException e) {
			isUpToDate = false;
		}
		if (isUpToDate) {
			updateButton.setText("No update available");
		} else {
			if (internetConnected) {
				updateButton.setText("Update available");
				updateButton.setEnabled(true);
			} else {
				updateButton.setText("No connection to server");
			}
		}
		System.out.println(localVersionExists);
		if (localVersionExists) {
			launchButton.setEnabled(true);
		}
		class ButtonClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(updateButton)) {
					// Update
					// Download new jar file
					File dlJar = null;
					try {
						dlJar = File.createTempFile("niaj", null);
					} catch (IOException e1) {
						System.err.println("Error creating temp file");
						System.exit(-1);
					}
					try {
						URL website = new URL(
								"https://raw.githubusercontent.com/twtduck/NeuroImageAnalysis/updates/NIA.jar");
						ReadableByteChannel rbc = Channels.newChannel(website.openStream());
						FileOutputStream fos = new FileOutputStream(dlJar);
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
						fos.close();
					} catch (IOException e2) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"Download failure. Check your network connection and try again.", "Download error",
								JOptionPane.ERROR_MESSAGE);
						System.exit(-1);
					}
					// Move new JAR in
					try {
						java.nio.file.Files.move(dlJar.toPath(), jarFile.toPath(),
								java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// Remove old version file
					currentVersionFile.delete();
					// Write new version file
					try {
						java.nio.file.Files.move(latestVersionFile.toPath(), currentVersionFile.toPath(),
								java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// Override buttons
					updateButton.setText("Update complete");
					launchButton.setEnabled(true);
					updateButton.setEnabled(false);
				} else {
					// Launch
					ProcessBuilder pb = new ProcessBuilder("java", "-jar", jarFile.getAbsolutePath());
					try {
						@SuppressWarnings("unused")
						Process p = pb.start();
						System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		updateButton.addActionListener(new ButtonClick());
		launchButton.addActionListener(new ButtonClick());
	}
}
