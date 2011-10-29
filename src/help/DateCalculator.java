package help;

import java.util.Date;

/**
 * This is a class that helps calculating differences between dates.
 */
public class DateCalculator
{
	/**
	 * Default Constructor: doesn't do anything.
	 */
	public DateCalculator(){}
	
	/**
	 * This method returns the result date after a certain amount of seconds has been added to a certain other date.
	 * @param oldDate
	 * The previous date.
	 * @param seconds
	 * The amount of seconds to be added.
	 * @return
	 * The result date.
	 */
	public static Date addTimeToDate(Date oldDate, int seconds){
		long oldMilliseconds = oldDate.getTime();
		Date newDate = new Date(oldMilliseconds + 1000*seconds);
		return newDate;
	}
}