package testing;

import javax.imageio.ImageIO;

public class TestImageIO {
	public static void main(String[] args) {
		String[] formats = ImageIO.getReaderFileSuffixes();
		for(String s : formats) {
			System.out.println(s);
		}
	}
}
