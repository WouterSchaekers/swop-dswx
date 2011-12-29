package scheduler;

import java.util.Observable;
import scheduler.task.TaskManager;
import users.UserManager;
import users.WarehouseAdmin;

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
	 * Constructor that will initilialise this TimeLord's time as being the
	 * given date.
	 */
	public TimeLord(HospitalDate date) {
		setSystemTime(date);
	}

	public HospitalDate getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(HospitalDate systemTime) {
		this.systemTime = systemTime;
		this.notifyObservers();
	}
}
