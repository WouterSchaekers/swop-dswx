package users;

import java.util.Date;
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
	public boolean canBeScheduledOn(Date start, Date stop) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void scheduleAt(TimeSlot t) {
		// TODO Auto-generated method stub
	}
	
	public TimeTable getTimeTable(){
		return this.timeTable;
	}
}