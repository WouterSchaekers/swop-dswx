package users;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeTable;
import scheduler.tasks.Task;
import system.Location;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected Location location_;
	protected TimeTable timeTable_;
	protected Collection<Task<?>> scheduledTasks_;

	protected SchedulableUser(String name, Location preference) throws InvalidNameException {
		super(name);
		this.timeTable_ = new TimeTable();
		this.scheduledTasks_ = new LinkedList<Task<?>>();
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

	public void setLocation(Location location) {
		this.location_ = location;
	}

	public Location getLocation() {
		return this.location_;
	}

}