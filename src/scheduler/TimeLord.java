package scheduler;

import java.util.Observable;
import exceptions.InvalidHospitalDateException;

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

	/**
	 * @return The SystemTime.
	 */
	public HospitalDate getSystemTime() {
		return systemTime.clone();
	}

	/**
	 * Adds one minute to the SystemTime. It will notify all the Observers.
	 */
	private void addOneMinute() {
		this.systemTime = new HospitalDate(this.systemTime.getTimeSinceStart() + HospitalDate.ONE_MINUTE);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * 
	 * @param hospitalDate
	 * @throws InvalidSystemTimeException
	 */
	public void setSystemTime(HospitalDate hospitalDate) throws InvalidHospitalDateException {
		if (!isValidNewSystemTime(hospitalDate))
			throw new InvalidHospitalDateException("This is not a valid systemTime.");
		while (getSystemTime().before(hospitalDate) && getSystemTime().getTimeBetween(hospitalDate) >= HospitalDate.ONE_MINUTE)
			addOneMinute();
	}

	private boolean isValidNewSystemTime(HospitalDate target) {
		return target != null && systemTime.before(target);
	}
}