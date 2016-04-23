package com.nia.images;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class Images {
	public static Icon getIcon(String path) {
		return new ImageIcon(Images.class.getClass().getResource(path));
	}
}
