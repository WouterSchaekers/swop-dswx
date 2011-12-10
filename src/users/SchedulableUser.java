package users;

import java.util.Date;
import exceptions.ImpossibleToScheduleException;
import exceptions.InvalidNameException;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected TimeTable timeTable;
	protected SchedulableUser(String name) throws InvalidNameException {
		super(name);
		this.timeTable = new TimeTable();
	}
	
	@Override
	public boolean canBeScheduledOn(Date startDate, Date stopDate) throws ImpossibleToScheduleException {;
		return timeTable.hasFreeSlotAt(startDate, stopDate);
	}
	
	@Override
	public void scheduleAt(TimeSlot timeSlot) throws ImpossibleToScheduleException {
		this.timeTable.addTimeSlot(timeSlot);
	}
	
	public TimeTable getTimeTable(){
		return this.timeTable;
	}
}