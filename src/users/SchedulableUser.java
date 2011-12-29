package users;

import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected TimeTable timeTable = new TimeTable();
	protected SchedulableUser(String name) throws InvalidNameException, InvalidTimeSlotException {
		super(name);
		this.timeTable = new TimeTable();
	}
	
	@Override
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate) throws InvalidSchedulingRequestException, InvalidTimeSlotException {;
		return timeTable.hasFreeSlotAt(startDate, stopDate);
	}
	
	@Override
	public void scheduleAt(TimeSlot timeSlot) throws InvalidSchedulingRequestException {
		this.timeTable.addTimeSlot(timeSlot);
	}
	
	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return new TimeTable(this.timeTable.getTimeSlots());
	}
	
	public void updateTimeTable(HospitalDate newDate){
		this.timeTable.updateTimeTable(newDate);
	}
}