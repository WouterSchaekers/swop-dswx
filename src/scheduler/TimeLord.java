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
	 * Sets a new SystemTime.
	 * 
	 * @param hospitalDate
	 *            The new SystemTime.
	 * @throws InvalidSystemTimeException
	 *             The given HospitalDate is not valid.
	 */
	public void setSystemTime(HospitalDate hospitalDate) throws InvalidHospitalDateException {
		if (!isValidNewSystemTime(hospitalDate))
			throw new InvalidHospitalDateException("This is not a valid systemTime.");
		while (getSystemTime().before(hospitalDate)
				&& getSystemTime().getTimeBetween(hospitalDate) >= HospitalDate.ONE_MINUTE)
			addOneMinute();
	}

	/**
	 * Checks whether the given HospitalDate is a valid HospitalDate.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate that has to be checked.
	 * @return True if the given HospitalDate is not null and after the old
	 *         SystemTime.
	 */
	private boolean isValidNewSystemTime(HospitalDate hospitalDate) {
		return hospitalDate != null && systemTime.before(hospitalDate);
	}
}