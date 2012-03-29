package users;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.tasks.Task;
import system.Location;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected Location location_;
	protected TimeTable _timeTable = new TimeTable();
	protected Collection<Task<?>> _scheduledTasks;

	 SchedulableUser(String name, Location preference) throws InvalidNameException {
		super(name);
		this._timeTable = new TimeTable();
		this._scheduledTasks = new LinkedList<Task<?>>();
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		;
		return _timeTable.hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException {
		this._timeTable.addTimeSlot(timeSlot);
	}

	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return new TimeTable(this._timeTable.getTimeSlots());
	}

	public void updateTimeTable(HospitalDate newDate) {
		this._timeTable.updateTimeTable(newDate);
	}
	
	public void setLocation(Location location){
		this.location_ = location;
	}
	
	public Location getLocation(){
		return this.location_;
	}
	
	
}