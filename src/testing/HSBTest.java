package testing;

import java.awt.Color;

import processing.ImageManipulation;

public class HSBTest {
	public static void main(String[] args) {
		System.out.println(ImageManipulation.getValueTestAccessor(Color.RED));
		
		System.out.println("HUE values:");
		float blue = ImageManipulation.getHueTestAccessor(Color.blue);
		float brown = ImageManipulation.getHueTestAccessor(Color.RED);
		System.out.println("Blue:  " + blue);
		System.out.println("Brown: " + brown);
	}
}
