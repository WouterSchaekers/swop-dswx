package users;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeTable;
import scheduler.tasks.Task;
import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * Class representing a SchedulableUser. This is a User that can be Scheduled.
 */
public abstract class SchedulableUser extends User implements Schedulable
{
	protected TimeTable timeTable_;
	protected Collection<Task<?>> scheduledTasks_;
	protected Location location_;
	public static final int START_WORK_HOUR = 9;
	public static final int STOP_WORK_HOUR = 17;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of the User.
	 * @param location
	 *            The Location of the User.
	 * @throws InvalidNameException
	 *             The given name is not valid.
	 * @throws InvalidLocationException
	 *             The given Location is not valid.
	 */
	protected SchedulableUser(String name, Location location) throws InvalidNameException, InvalidLocationException {
		super(name);
		if (!validLocation(location))
			throw new InvalidLocationException("Invalid location given to schedulable user!");
		this.timeTable_ = new TimeTable();
		this.scheduledTasks_ = new LinkedList<Task<?>>();
		this.location_ = location;
	}

	/**
	 * Checks whether the given Location is valid or not.
	 * 
	 * @param location
	 *            The Location that has to be checked.
	 * @return True if the given Location is not null.
	 */
	private boolean validLocation(Location location) {
		return location != null;
	}

	/**
	 * Checks whether the User can be scheduled between the two given HospitalDates.
	 */
	@Override
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate) {
		return timeTable_.hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public TimeTable getTimeTable() {
		TimeTable timeTable = null;
		try {
			timeTable = new TimeTable(this.timeTable_.getTimeSlots());
		} catch (InvalidTimeSlotException e) {
			System.out.println(e);
		}
		return timeTable;
	}

	@Override
	public void updateTimeTable(HospitalDate newDate) {
		this.timeTable_.updateTimeTable(newDate);
	}

	@Override
	public boolean mustBeBackToBack() {
		return true;
	}
}