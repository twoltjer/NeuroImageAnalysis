package logging;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;

import system.Ops;

/**
 * This class provides an interface for writing to log files, and changing the
 * file where the logs are kept. To use it, make sure that at the beginning of
 * your program you call Log.start() and at the end you call Log.close(). Also,
 * this class is probably the best way to write to the console, because it logs
 * that as well.
 * 
 * @author Thomas Woltjer
 */
public abstract class Log {
	// Logging configuration
	final static String DEFAULT_LOG_PATH = "startlog.txt";
	final static boolean ENABLE_DEBUG_LOG = true;

	// Declare logging levels
	public final static int ERROR = -1;
	public final static int STANDARD = 0;
	public final static int DEBUG = 1;
	public final static int CONSOLE = 2;

	// Declare different timestamps
	private static final int TIMESTAMP_LONG = 0;
	private static final int TIMESTAMP_SHORT = 1;

	// Create logging objects
	private static String logPath = DEFAULT_LOG_PATH;
	private static PrintWriter log;
	private static boolean isInstantiated = false;

	/**
	 * Start logging output
	 */
	public static void start() {
		open();
		log.println("Log started at " + Calendar.getInstance().getTime());
	}

	/**
	 * Open a log file. This is private because it should only be called by the
	 * start() and changeLogFile() methods.
	 */
	private static void open() {
		try {
			log = new PrintWriter(logPath);
			Log.isInstantiated = true;
		} catch (FileNotFoundException e) {
			// If this happens there are problems writing in the run directory
			e.printStackTrace();
		}
	}

	/**
	 * Close the log, and actually write whatever is left in the buffer to the
	 * file.
	 */
	public static void close() {
		Log.write("Closing log", Log.STANDARD);
		log.close();
	}

	/**
	 * Write a line of text to the log file, and possibly the console. This
	 * should be used instead of System.out.
	 * 
	 * @param s
	 *            The text to write to the log file
	 * @param level
	 *            -1 is error, 0 is standard, 1 is debug, 2 is console These are
	 *            all declared as public constants for this class
	 */
	public static void write(String s, int level) {

		String filePrint = "";

		// Java really likes to generate warnings
		@SuppressWarnings("unused")
		boolean writeThisMessageToWindow = (Log.ENABLE_DEBUG_LOG || level != Log.DEBUG);

		if (LogWindowManager.isInstantiated && writeThisMessageToWindow) {
			String windowPrint = genLevelStamp(level, s) + "  " + genTimeStamp(Log.TIMESTAMP_SHORT) + "  " + s;
			LogWindowManager.write(windowPrint);
		}

		filePrint += genLevelStamp(level, s) + " \t" + genTimeStamp(Log.TIMESTAMP_LONG) + " \t" + s;
		log.println(filePrint);
		
		// If the program encounters an error, close it to prevent damage
		if (level == Log.ERROR)
			Ops.exit();
		
		LogWindowManager.getScroolpane().getVerticalScrollBar().setValue(LogWindowManager.getScroolpane().getVerticalScrollBar().getMaximum());;
	}

	private static String genLevelStamp(int level, String writeMsg) {
		String print = "[";
		if (level == -1)
			print += "err";
		else if (level == 0)
			print += "std";
		else if (level == 1)
			print += "dbg";
		else if (level == 2)
			print += "cns";
		else {
			Log.write(("Bad log write level: \"" + writeMsg + "\""), Log.ERROR);
			Ops.exit();
		}
		print += "]";
		return print;
	}

	/**
	 * Change log file location. This is a save way of doing it, because it
	 * closes the PrintWriter for the old logfile, and makes a new one for the
	 * new log file.
	 * 
	 * @param newLocation
	 */
	public static void changeLogFile(String newLocation) {
		log.println("[log]\t" + genTimeStamp(Log.TIMESTAMP_LONG) + "\tLog file changed to " + newLocation);
		Log.close();
		String oldLog = logPath;
		logPath = newLocation;
		Log.open();
		log.println("[log]\t" + genTimeStamp(Log.TIMESTAMP_LONG) + "\tLog continued from " + oldLog);
	}

	/**
	 * Creates a timestamp. Returns it in one of two formats. These formats are
	 * either long (for the log file) or short (for the log window).
	 * 
	 * @param timeFormat
	 *            accepts either Log.TIMESTAMP_LONG or Log.TIMESTAMP_SHORT
	 * @return the generated timestamp
	 */
	private static String genTimeStamp(int timeFormat) {
		Calendar c = Calendar.getInstance();
		String timestamp = "[";

		String year = Integer.toString(c.get(Calendar.YEAR));
		timestamp += year + "-";

		String month = Integer.toString(c.get(Calendar.MONTH));
		while (month.length() < 2)
			month = "0" + month;
		timestamp += month + "-";

		String date = Integer.toString(c.get(Calendar.DATE));
		while (date.length() < 2)
			date = "0" + date;
		timestamp += date + " ";

		int rawHour = c.get(Calendar.HOUR);
		if (c.get(Calendar.AM_PM) == Calendar.PM)
			rawHour += 12;
		String hour = Integer.toString(rawHour);
		while (hour.length() < 2)
			hour = "0" + hour;
		timestamp += hour + ":";

		String minute = Integer.toString(c.get(Calendar.MINUTE));
		while (minute.length() < 2)
			minute = "0" + minute;
		timestamp += minute + ":";

		String second = Integer.toString(c.get(Calendar.SECOND));
		while (second.length() < 2)
			second = "0" + second;
		timestamp += second + ".";

		String milli = Integer.toString(c.get(Calendar.MILLISECOND));
		while (milli.length() < 3)
			milli = "0" + milli;
		timestamp += milli + "]\t";

		if (timeFormat == Log.TIMESTAMP_LONG) {
			return timestamp;
		} else if (timeFormat == Log.TIMESTAMP_SHORT) {
			String tmstmp = "[" + hour + ":" + minute + ":" + second + "]\t ";
			return tmstmp;
		} else {
			Log.write("Undeclared timestamp format is being requested", Log.ERROR);
			return null;
		}
	}

	public static boolean isInstantiated() {
		return Log.isInstantiated;
	}
}