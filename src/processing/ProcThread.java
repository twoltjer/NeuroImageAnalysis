package processing;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import global.Config;
import global.DebugMessenger;
import global.RuntimeConfig;

public class ProcThread extends Thread {
	public static int scannedImagesCount = 0;

	@Override
	public void run() {
		PrintWriter outFileWriter = null;
		try {
			DebugMessenger.out("OUTPUT FILE IS NULL OBJECT: " + (RuntimeConfig.outputFile == null));
			outFileWriter = new PrintWriter(RuntimeConfig.outputFile);
		} catch (FileNotFoundException e) {
			DebugMessenger.out("Something went wrong with creating an output file");
			e.printStackTrace();
		}
		outFileWriter.print("Threshold,");
		for(int i = 0; i < 100; i += Config.OUTPUT_FILE_THRESHOLD_INC) {
			outFileWriter.print(i + ",");
		}
		outFileWriter.println("100;");
		for(int imgNum = 0; imgNum < RuntimeConfig.imageFiles.size(); imgNum++) {
			outFileWriter.print(RuntimeConfig.imageFiles.get(imgNum).getName());
			for(int thresh = 0; thresh <= 100; thresh += Config.OUTPUT_FILE_THRESHOLD_INC) {
				BufferedImageContainer bic = new BufferedImageContainer(BufferedImageContainer.DM_MONOCHROME, thresh, imgNum, false);
				bic.bufferImage();
				float posVal = ImageManipulation.countPositive(Color.BLACK, bic.image);
				bic.unbufferImage();
				outFileWriter.print("," + posVal );
				scannedImagesCount += 1;
			}
			outFileWriter.println(";");
		}
		outFileWriter.close();
		RuntimeConfig.isScanningImages = false;
	}
}
