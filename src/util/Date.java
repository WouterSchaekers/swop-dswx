package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Class representing dates
 * @author Dieter
 *The count is based on the gregorian calender starting at 0 BC
 *
 */
public class Date {
 public	enum day
	{
		Monday,
		Thuesday,
		Wensday,
		Thursday,
		Friday,
		Saturday,
		Sunday;
	}
	public enum month{
		January (31),
		February (30),
		March(31),
		April(30),
		May(31),
		June(30),
		July(31),
		August(31),
		September(30),
		October(31),
		November(30),
		December(31);
		private month(int days)
		{
			this.days = days;
		}
		int days;
		public int days(int year)
		{
			if(this.equals(February)&&isSkippingYear(year))
				return this.days+1;
			return this.days;
			
		}
		private boolean isSkippingYear(int year) {
			if(year%4!=0)
				return false;
			if(year%100==0)
				return false;
			return true;
		}
		public Collection<month> monthsBefore(month m)
		{
			ArrayList<month> list = new ArrayList<Date.month>();
			for(month s:this.values())
				if(s.equals(m))
					return list;
				else
					list.add(s);
			return list;
		}
	}
	enum mode{
		ddmmyyyy,
		mmddyyyy;
		
	}
	int day;
	private day d;
	private month m;
	int year;
	/**
	 * Standard constructor for a date object
	 * @param d
	 * 	The day of the date object, representing the # of days in this month
	 * @param m
	 * 	The month of the date object
	 * @param year
	 * 	The year of the date object
	 * 	@Pre
	 *  week is an integer between 0 and 26
	 *  Example: 
	 *  first of january 2011
	 *  new Date(1,January,2011)
	 */
	public Date(int day,month m,int year)
	{
		
	}
	public Date(String date,mode m)
	{
		
	}
	public day getWeekDay()
	{
		return null;
	}
	public int getDayMonth()
	{
		return day;
		
	}
	public int getDayYear()
	{
		return day;
	}
	public long daysTo(Date other)
	{
	return 0;	
	}
	public java.sql.Date toSqlDate()
	{
		Date d = new Date(1,month.January,1973);
		return new java.sql.Date(d.daysTo(this));
	}
	public static void main(String[] args) {
		System.out.println("braaa");
	}
	
}
