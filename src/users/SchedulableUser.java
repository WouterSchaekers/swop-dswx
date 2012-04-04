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
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected TimeTable timeTable_;
	protected Collection<Task<?>> scheduledTasks_;
	protected Location location_;
	public static final int START_WORK_HOUR = 9;
	public static final int STOP_WORK_HOUR = 17;
	
	protected SchedulableUser(String name, Location location) throws InvalidNameException, InvalidLocationException {
		super(name);
		if(!validLocation(location))
			throw new InvalidLocationException();
		this.timeTable_ = new TimeTable();
		this.scheduledTasks_ = new LinkedList<Task<?>>();
		this.location_ = location;
	}

	private boolean validLocation(Location location) {
		return location!=null;
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		;
		return timeTable_.hasFreeSlotAt(startDate, stopDate);
	}

	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return new TimeTable(this.timeTable_.getTimeSlots());
	}

	public void updateTimeTable(HospitalDate newDate) {
		this.timeTable_.updateTimeTable(newDate);
	}
}