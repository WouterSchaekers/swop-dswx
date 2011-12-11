package scheduler;

import java.util.GregorianCalendar;

public class HospitalDate
{
	public final static int START_YEAR = 2011;
	public final static int START_MONTH = 11;
	public final static int START_DAY = 8;
	public final static int START_HOUR = 8;
	public final static int START_MINUTE = 0;
	public final static int START_SECOND = 0;
	public final static HospitalDate START_OF_TIME = new HospitalDate();
	public final static HospitalDate END_OF_TIME = new HospitalDate(Long.MAX_VALUE);
	private GregorianCalendar gregorianCalendar;
	
	/**
	 * Creates a new HospitalDate, with the default start time.
	 */
	public HospitalDate(){
		this(START_YEAR, START_MONTH, START_DAY, START_HOUR, START_MINUTE, START_SECOND);
	}
	
	/**
	 * Creates a new HostpitalDate, with the given new time in UTC milliseconds from the epoch.
	 * 
	 * @param milliseconds
	 * 		The amount of milliseconds from the first epoch.
	 */
	public HospitalDate(long milliseconds){
		gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTimeInMillis(milliseconds);
	}
	
	/**
	 * Creates a new HospitalDate, with the given date objects.
	 * 
	 * @param year
	 * 		The year.
	 * @param month
	 * 		The month of the year.
	 * @param day
	 * 		The day of the month.
	 * @param hour
	 * 		The hour of the day.
	 * @param minute
	 * 		The minute of the hour.
	 * @param second
	 * 		The second of the minute.
	 */
	public HospitalDate(int year, int month, int day, int hour, int minute, int second){
		gregorianCalendar = new GregorianCalendar(year, month, day, hour, minute, second);
	}
	
	/**
	 * @return
	 * 		The year.
	 */
	public int getYear(){
		return gregorianCalendar.get(GregorianCalendar.YEAR);
	}
	
	/**
	 * Sets the year.
	 * 
	 * @param year
	 * 		The year.
	 */
	public void setYear(int year){
		this.setTime(year, this.getMonth(), this.getDay(), this.getHour(), this.getMinute(), this.getSecond());
	}
	
	/**
	 * @return
	 * 		The month of the year.
	 */
	public int getMonth(){
		return gregorianCalendar.get(GregorianCalendar.MONTH);
	}
	
	/**
	 * Sets the month.
	 * 
	 * @param month
	 * 		The month of the year.
	 */
	public void setMonth(int month){
		this.setTime(this.getYear(), month, this.getDay(), this.getHour(), this.getMinute(), this.getSecond());
	}
	
	/**
	 * @return
	 * 		The day of the month.
	 */
	public int getDay(){
		return gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	/**
	 * Sets the day.
	 * 
	 * @param day
	 * 		The day of the month.
	 */
	public void setDay(int day){
		this.setTime(this.getYear(), this.getMonth(), day, this.getHour(), this.getMinute(), this.getSecond());
	}
	
	/**
	 * @return
	 * 		The hour of the day.
	 */
	public int getHour(){
		return gregorianCalendar.get(GregorianCalendar.HOUR);
	}
	
	/**
	 * Sets the hour.
	 * 
	 * @param hour
	 * 		The hour of the day.
	 */
	public void setHour(int hour){
		this.setTime(this.getYear(), this.getMonth(), this.getDay(), hour, this.getMinute(), this.getSecond());
	}
	
	/**
	 * @return
	 * 		The minute of the hour.
	 */
	public int getMinute(){
		return gregorianCalendar.get(GregorianCalendar.MINUTE);
	}
	
	/**
	 * Sets the minute.
	 * 
	 * @param minute
	 * 		The minute of the hour.
	 */
	public void setMinute(int minute){
		this.setTime(this.getYear(), this.getMonth(), this.getDay(), this.getHour(), minute, this.getSecond());
	}
	
	/**
	 * @return
	 * 		The second of the minute.
	 */
	public int getSecond(){
		return gregorianCalendar.get(GregorianCalendar.SECOND);
	}
	
	/**
	 * Sets the second.
	 * 
	 * @param second
	 * 		The second of the minute.
	 */
	public void setSecond(int second){
		this.setTime(this.getYear(), this.getMonth(), this.getDay(), this.getHour(), this.getMinute(), second);
	}
	
	/**
	 * Sets the time.
	 * 
	 * @param year
	 * 		The year.
	 * @param month
	 * 		The month of the year.
	 * @param day
	 * 		The day of the month.
	 */
	public void setTime(int year, int month, int day){
		this.setTime(year, month, day, this.getHour());
	}
	
	/**
	 * Sets the time.
	 * 
	 * @param year
	 * 		The year.
	 * @param month
	 * 		The month of the year.
	 * @param day
	 * 		The day of the month.
	 * @param hour
	 * 		The hour of the day.
	 */
	public void setTime(int year, int month, int day, int hour){
		this.setTime(year, month, day, hour, this.getMinute());
	}
	
	/**
	 * Sets the time.
	 * 
	 * @param year
	 * 		The year.
	 * @param month
	 * 		The month of the year.
	 * @param day
	 * 		The day of the month.
	 * @param hour
	 * 		The hour of the day.
	 * @param minute
	 * 		The mintue of the hour.
	 */
	public void setTime(int year, int month, int day, int hour, int minute){
		this.setTime(year, month, day, hour, minute, this.getSecond());
	}
	
	/**
	 * Sets the time.
	 * 
	 * @param year
	 * 		The year.
	 * @param month
	 * 		The month of the year.
	 * @param day
	 * 		The day of the month.
	 * @param hour
	 * 		The hour of the day.
	 * @param minute
	 * 		The mintue of the hour.
	 * @param second
	 * 		The second of the minute.
	 */
	public void setTime(int year, int month, int day, int hour, int minute, int second){
		gregorianCalendar.set(year, month, day, hour, minute, second);
	}
	
	/**
	 * @return
	 * 		The amounf of UTC milliseconds from the epoch.
	 */
	public long getTotalMillis(){
		return gregorianCalendar.getTimeInMillis();
	}
	
	/**
	 * @param hospitalDate
	 * 		The other hospitaldate.
	 * @return
	 * 		True if this hospitaldate occurs before the other hospitaldate.
	 */
	public boolean before(HospitalDate hospitalDate){
		return this.getTotalMillis() < hospitalDate.getTotalMillis();
	}
	
	/**
	 * @return
	 * 		The current date as a string.
	 */
	public String toString(){
		return this.getYear() + "-" + this.getMonth() + "-" + this.getDay() + " " + this.getHour() + ":" + this.getMinute() + ":" + this.getSecond();
	}
}