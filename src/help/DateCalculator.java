package help;

import java.util.Date;

public class DateCalculator
{
	public DateCalculator(){}
	public static Date addTimeToDate(Date oldDate, int seconds){
		long oldMilliseconds = oldDate.getTime();
		Date newDate = new Date(oldMilliseconds + 1000*seconds);
		return newDate;
	}
}