package testing;

import gui.GUIThread;

public class MemBarMaker {
	public static GUIThread memBarThread;
	public static void main(String[] args) {
		memBarThread = new GUIThread();
		memBarThread.startThread(GUIThread.MEMORY_USAGE_WINDOW);
	}
}
