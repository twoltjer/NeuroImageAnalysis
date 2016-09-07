package processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import global.DebugMessenger;

public class BufferedImageContainer {
	public int DM;
	public int thresh;
	public int indexNumber;
	public boolean scaled = false;
	public BufferedImage image;
	public File imageFile;
	public BufferedImageContainer(int DM, int thresh, int indexNumber) {
		this.DM = DM;
		this.thresh = thresh;
		this.indexNumber = indexNumber;
	}
	
	public void bufferImage() {
		try {
			image = ImageIO.read(imageFile);
			if(scaled) {
				int imageSize = image.getHeight() * image.getWidth();
				//TODO: Finish writing this
			}
		} catch (IOException e) {
			DebugMessenger.out("There was a problem reading the image");
			e.printStackTrace();
		}
	}
	
	public void unbufferImage() {
		
	}
	
	private void getImageFile() {
		
	}
	
}
