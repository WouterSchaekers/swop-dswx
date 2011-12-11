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
	private GregorianCalendar gregorianCalendar;
	
	/**
	 * Creates a new HospitalDate, with the default start time.
	 */
	public HospitalDate(){
		this(START_YEAR, START_MONTH, START_DAY, START_HOUR, START_MINUTE, START_SECOND);
	}
	
	/**
	 * Creates a new HostpitalDate, with the given 
	 * @param milliseconds
	 */
	public HospitalDate(long milliseconds){
		gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTimeInMillis(milliseconds);
	}
	
	public HospitalDate(int year, int month, int day, int hour, int minute, int second){
		gregorianCalendar = new GregorianCalendar(year, month, day, hour, minute, second);
	}
	
	public int getYear(){
		return gregorianCalendar.get(GregorianCalendar.YEAR);
	}
	
	public void setYear(int year){
		this.setTime(year, this.getMonth(), this.getDay(), this.getHour(), this.getMinute(), this.getSecond());
	}
	
	public int getMonth(){
		return gregorianCalendar.get(GregorianCalendar.MONTH);
	}
	
	public void setMonth(int month){
		this.setTime(this.getYear(), month, this.getDay(), this.getHour(), this.getMinute(), this.getSecond());
	}
	
	public int getDay(){
		return gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public void setDay(int day){
		this.setTime(this.getYear(), this.getMonth(), this.getDay(), this.getHour(), this.getMinute(), this.getSecond());
	}
	
	public int getHour(){
		return gregorianCalendar.get(GregorianCalendar.HOUR);
	}
	
	public void setHour(int hour){
		this.setTime(this.getYear(), this.getMonth(), this.getDay(), hour, this.getMinute(), this.getSecond());
	}
	
	public int getMinute(){
		return gregorianCalendar.get(GregorianCalendar.MINUTE);
	}
	
	public void setMinute(int minute){
		this.setTime(this.getYear(), this.getMonth(), this.getDay(), this.getHour(), minute, this.getSecond());
	}
	
	public int getSecond(){
		return gregorianCalendar.get(GregorianCalendar.SECOND);
	}
	
	public void setSecond(int second){
		this.setTime(this.getYear(), this.getMonth(), this.getDay(), this.getHour(), this.getMinute(), second);
	}
	
	public void setTime(int year, int month, int day){
		this.setTime(year, month, day, this.getHour());
	}
	
	public void setTime(int year, int month, int day, int hour){
		this.setTime(year, month, day, hour, this.getMinute());
	}
	
	public void setTime(int year, int month, int day, int hour, int minute){
		this.setTime(year, month, day, hour, minute, this.getSecond());
	}
	
	public void setTime(int year, int month, int day, int hour, int minute, int second){
		gregorianCalendar.set(year, month, day, hour, minute, second);
	}
	
	public long getTotalMillis(){
		return gregorianCalendar.getTimeInMillis();
	}
}