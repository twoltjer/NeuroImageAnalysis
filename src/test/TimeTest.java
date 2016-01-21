package test;

import java.util.Calendar;

/**
 * This tests writing time stamps. It will continue writing them until the program is terminated. 
 * @author twtduck
 */
public class TimeTest {
	public static void main(String[] args)
	{
		while(true)
		{
			Calendar c = Calendar.getInstance();
			String timestamp = "[";
			
			// In case the time is after noon, fix the hours (the Calendar doesn't run on 24 hour time).
			int rawHour = c.get(Calendar.HOUR);
			if(c.get(Calendar.AM_PM) == Calendar.PM)
				rawHour += 12;
			String hour = Integer.toString(rawHour);
			while(hour.length() < 2)
				hour = "0" + hour;
			timestamp += hour + ":";
			
			String minute = Integer.toString(c.get(Calendar.MINUTE));
			while(minute.length() < 2)
				minute = "0" + minute;
			timestamp += minute + ":";
			
			String second = Integer.toString(c.get(Calendar.SECOND));
			while(second.length() < 2)
				second = "0" + second;
			timestamp += second + ".";
			
			String milli = Integer.toString(c.get(Calendar.MILLISECOND));
			while(milli.length() < 3)
				milli = "0" + milli;
			timestamp += milli + "]\t";
			
			System.out.println(timestamp);
		}
	}
}
