package testing;

import global.DebugMessenger;

public class UnitTests {
	public static int passCount = 0;
	public static int testCount = 0;
	public static final String SUCCESS_STRING = "\n\t\t\t\t\t[PASS]";
	public static final String FAILURE_STRING = "\n\t\t\t\t\t[FAIL]";

	/**
	 * Runs all tests
	 * @param args pointless arguments
	 */
	public static void main(String[] args) {
		testDebugger();
		System.out.println("Tests complete. " + passCount + " of " + testCount + " tests passed.");
	}
	
	public static void testDebugger() {
		try {
			DebugMessenger.out("Test message");
			passCount += 1;
			System.out.println(SUCCESS_STRING);
		} catch(Exception e) {
			System.out.println(FAILURE_STRING);
		}
		testCount += 1;
		
		try {
			DebugMessenger.outNoNewLine("Test message");
			passCount += 1;
			System.out.println(SUCCESS_STRING);
		} catch(Exception e) {
			System.out.println(FAILURE_STRING);
		}
		testCount += 1;
	}
	
	public static void testGUICreation() {
		// test chooser
		
	}
	
	
	public static void testButtonClicks() {
		
	}
	
	public static void testImageProcessing() {
		
	}
}
