package global;
/**
 * Writes messages to standard output, for use in debugging. 
 * Only functions when Config.ENABLE_DEBUGGING_OUTPUT is set to true
 * @author Thomas Woltjer
 */
public abstract class DebugMessenger {
	/**
	 * As the printer starts at the beginning of a blank line, it is as if a new line was just printed. 
	 * Set the variable to true in order to correctly format the message, with the "DEBUG" tag
	 */
	private static boolean lastOutWasNewLine = true;
	/**
	 * Write a debug message to standard out
	 * @param message the message
	 */
	public static void out(String message) {
		DebugMessenger.outNoNewLine(message + "\n");
		lastOutWasNewLine = true;
	}
	
	/**
	 * Write a debug message to standard out, but don't print a new line
	 * @param message the message
	 */
	public static void outNoNewLine(String message) {
		if(Config.DEBUG_OUTPUT_ENABLED) {
			String printer = "";
			if(lastOutWasNewLine) {
				printer += "DEBUG: ";
			}
			printer += message;
			System.out.print(printer);
		}
		lastOutWasNewLine = false;
	}
}
