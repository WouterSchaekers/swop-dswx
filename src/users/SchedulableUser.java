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
	/**
	 * The hour on which every SchedulableUser starts to work.
	 */
	public static final int START_WORK_HOUR = 9;
	/**
	 * The hour on which every SchedulableUser stops to work.
	 */
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
	 * Checks whether the Schedulable can be Scheduled on a given TimeSlot.
	 * 
	 * @param startDate
	 *            The HospitalDate on which the TimeSlot starts.
	 * @param stopDate
	 *            The HospitalDate on which the TimeSlot stops.
	 * @return True if the Schedulable is free between the given HospitalDates.
	 */
	@Override
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate) {
		return timeTable_.hasFreeSlotAt(startDate, stopDate);
	}

	/**
	 * @return The TimeTable of the Schedulable.
	 */
	@Override
	public TimeTable getTimeTable() {
		TimeTable timeTable = null;
		try {
			timeTable = new TimeTable(this.timeTable_.getTimeSlots());
		} catch (InvalidTimeSlotException e) {
			throw new Error(e);
		}
		return timeTable;
	}

	/**
	 * Updates this TimeTable, so it stays clean. All the TimeSlots that are
	 * finished will be deleted.
	 * 
	 * @param newDate
	 *            The HospitalDate on which all the TimeSlots will have to be
	 *            checked against.
	 */
	@Override
	public void updateTimeTable(HospitalDate newDate) {
		this.timeTable_.updateTimeTable(newDate);
	}

	/**
	 * @return True if the Schedulalbe must be scheduled back to back.
	 */
	@Override
	public boolean mustBeBackToBack() {
		return true;
	}

	protected boolean fallsInWorkingHours(HospitalDate hospitalDate) {
		HospitalDate startDummy = new HospitalDate(0, 0, 0, SchedulableUser.START_WORK_HOUR, 0, 0);
		HospitalDate stopDummy = new HospitalDate(0, 0, 0, SchedulableUser.STOP_WORK_HOUR, 0, 0);
		HospitalDate currentDummy = makeDummyDate(hospitalDate);
		if (!currentDummy.before(startDummy) && !currentDummy.after(stopDummy))
			return true;
		return false;
	}

	private HospitalDate getNextStartWorkingHour(HospitalDate hospitalDate) {
		HospitalDate nextDate = new HospitalDate(hospitalDate.getTimeSinceStart() + HospitalDate.ONE_DAY);
		return new HospitalDate(nextDate.getYear(), nextDate.getMonth(), nextDate.getDay(),
				SchedulableUser.START_WORK_HOUR, 0, 0);
	}

	private HospitalDate makeDummyDate(HospitalDate hospitalDate) {
		return new HospitalDate(0, 0, 0, hospitalDate.getHour(), hospitalDate.getMinute(), hospitalDate.getSecond());
	}
}