package test;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class JAITest {
	public static void main(String[] args) {
		String[] suff = ImageIO.getReaderFileSuffixes();
		ArrayList<String> al = new ArrayList<String>();
		for(String s : suff) {
			al.add(s);
		}
		System.out.println("Read suffixes:\t" + al);
		
		suff = ImageIO.getWriterFileSuffixes();
		al.clear();
		for(String s : suff) {
			al.add(s);
		}
		System.out.println("Write suffixes:\t" + al);
	}
}
