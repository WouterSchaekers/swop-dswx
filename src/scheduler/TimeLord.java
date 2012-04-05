package scheduler;

import java.util.Observable;
import exceptions.InvalidSystemTimeException;

/**
 * This class can be used to store the current system time in.
 */
public class TimeLord extends Observable
{
	private HospitalDate systemTime;

	/**
	 * Constructor that will create a new TimeLord that will have its current
	 * system time as the beginning of time (@see: HospitalDate)
	 */
	public TimeLord() {
		this(new HospitalDate());
	}

	/**
	 * Constructor that will initialise this TimeLord's time as being the given
	 * date.
	 */
	public TimeLord(HospitalDate date) {
		systemTime = date;
	}

	public HospitalDate getSystemTime() {
		return systemTime.clone();
	}

	private void addOneMinute() {
		this.systemTime = new HospitalDate(this.systemTime.getTimeSinceStart() + HospitalDate.ONE_MINUTE);
		this.setChanged();
		this.notifyObservers();

	}

	/**
	 * 
	 * @param target
	 * @throws InvalidSystemTimeException
	 */
	public void setSystemTime(HospitalDate target) throws InvalidSystemTimeException {
		if (!isValidNewSystemTime(target))
			throw new InvalidSystemTimeException("This is not a valid systemTime.");
		while (getSystemTime().before(target) && getSystemTime().getTimeBetween(target) >= HospitalDate.ONE_MINUTE)
			addOneMinute();
	}

	private boolean isValidNewSystemTime(HospitalDate target) {
		return target != null && systemTime.before(target);
	}
}