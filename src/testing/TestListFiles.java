package testing;

import java.io.File;
import java.util.ArrayList;

public class TestListFiles {
	public static int tabs = 0;
	public static ArrayList<Boolean> listEnd = new ArrayList<Boolean>();
	public static void main(String[] args) {
		File tempFile= new File(".");
		
		System.out.println("Almost Listing files in: " + tempFile.getAbsolutePath());
		
		File thisDir = tempFile.getAbsoluteFile().getParentFile();
		System.out.println("Listing files in: " + thisDir.getAbsolutePath());
		listFiles(thisDir);
		
	}
	
	//TODO: This function has formatting problems with the displayed list. Fix them sometime.
	public static void listFiles(File dir) {
		// print dir name
		for(int i = 0; i < tabs - 1; i++) {
			System.out.print("\u2502   ");
		}
		if(tabs > 0) {
			System.out.print("\u251c\u2500\u2500 ");
		}
		System.out.println(dir.getName());
		
		// print dir contents
		
		tabs += 1;
		listEnd.add(new Boolean(false));
		File[] dirContents = dir.listFiles();
		for(int i = 0; i < dirContents.length; i++) {
			if(i + 1 == dirContents.length) {
				listEnd.set(listEnd.size() - 1, new Boolean(true));
			}
			if(!(dirContents[i].getName().charAt(0) == '.')) {
				if(dirContents[i].isDirectory()) {
					listFiles(dirContents[i]);
				} else {
					for(int t = 0; t < tabs - 1; t++) {
						if(listEnd.get(t)) {
							System.out.print("    ");
						} else {
							System.out.print("\u2502   "); 
						}
					}
					if(tabs > 0) {
						if(listEnd.get(tabs - 1)) {
							System.out.print("\u2514\u2500\u2500 ");
						} else {
							System.out.print("\u251c\u2500\u2500 ");
						}
						
					}
					System.out.println(dirContents[i].getName());
				}
			}
			
		}
		listEnd.remove(listEnd.size() - 1);
		tabs -= 1;
	}

}
