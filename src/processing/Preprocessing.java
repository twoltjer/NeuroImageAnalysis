package processing;

import java.io.File;

import javax.imageio.ImageIO;

import global.DebugMessenger;
import global.RuntimeConfig;
import testing.TestPreviewer;

/**
 * Class to run preprocessing methods
 * @author Thomas
 *
 */
public class Preprocessing {
	public static void main(String[] args) {
		final String[] SUPPORTED_TYPES = ImageIO.getReaderFileSuffixes();
		for(String type : SUPPORTED_TYPES) {
			System.out.println(type);
		}
		RuntimeConfig.imageDir = new File(TestPreviewer.IMAGE_DIRECTORY);
		readImageDirectory();
	}
	/**
	 * Reads the image directory, and adds images there to an array
	 */
	public static void readImageDirectory() {
		final String[] SUPPORTED_TYPES = ImageIO.getReaderFileSuffixes();
		File[] dirContents = RuntimeConfig.imageDir.listFiles();
		DebugMessenger.out("Found " + dirContents.length + " files in image directory");
		for(String filetype : SUPPORTED_TYPES) {
			DebugMessenger.out("Checking for " + filetype + " files in image directory");
			for(File f : dirContents) {
				if(f.getName().endsWith("." + filetype)) {
					DebugMessenger.out("Found file: " + f.getName());
					RuntimeConfig.imageFiles.add(f);
					
				} else {
					DebugMessenger.out("File not the correct format. Skipping. Note: This is not an error.");
				}
			}
		}
	}
}
