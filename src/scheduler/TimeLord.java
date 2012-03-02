package scheduler;

import java.util.Observable;

/**
 * This class can be used to store the current system time in.
 */
public class TimeLord extends Observable
{
	private HospitalDate systemTime ;

	/**
	 * Constructor that will create a new TimeLord that will have its current
	 * system time as the beginning of time (@see: HospitalDate)
	 */
	public TimeLord() {
		this(new HospitalDate());
	}

	/**
	 * Constructor that will initilialise this TimeLord's time as being the
	 * given date.
	 */
	public TimeLord(HospitalDate date) {
		systemTime = date;
	}

	public HospitalDate getSystemTime() {
		return systemTime;
	}
	//XXX: hier waren wwe mee bezig
	private void addOneMinute()
	{
		this.systemTime = new HospitalDate(this.systemTime.getTimeSinceStart()+HospitalDate.ONE_MINUTE);
		this.notifyObservers();	

	}
	/**
	 * 
	 * @param target
	 */  
	public void setSystemTime(HospitalDate target) {
		while(getSystemTime().before(target) && getSystemTime().getTimeBetween(target)>=HospitalDate.ONE_MINUTE)
			addOneMinute();
		
	}
}
