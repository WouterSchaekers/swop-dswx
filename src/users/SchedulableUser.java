package users;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.tasks.ScheduledTask;
import system.Whereabouts;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected Whereabouts _preference;
	protected Whereabouts _location;
	protected TimeTable _timeTable = new TimeTable();
	protected Collection<ScheduledTask> _scheduledTasks;

	protected SchedulableUser(String name, Whereabouts preference) throws InvalidNameException {
		super(name);
		this._preference = preference;
		this._timeTable = new TimeTable();
		this._scheduledTasks = new LinkedList<ScheduledTask>();
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
	
	public void setLocation(Whereabouts location){
		this._location = location;
	}
	
	public Whereabouts getLocation(){
		return this._location;
	}
	
	public abstract Whereabouts getLocationAt(HospitalDate hospitalDate);

	public Collection<ScheduledTask> getScheduledTasks() {
		return this._scheduledTasks;
	}

	public void addScheduledTask(ScheduledTask scheduledTask) {
		this._scheduledTasks.add(scheduledTask);
	}
}