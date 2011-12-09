package users;

import java.util.Date;
import exceptions.ImpossibleToScheduleException;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected TimeTable timeTable;
	protected SchedulableUser(String name) {
		super(name);
		this.timeTable = new TimeTable();
	}
	
	@Override
	public boolean canBeScheduledOn(Date startDate, Date stopDate) {
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