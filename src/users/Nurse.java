package users;

import java.util.Date;
import scheduler.timetables.TimeSlot;
import task.Schedulable;

public class Nurse extends User implements Schedulable
{
	public Nurse(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Nurse;
	}

	@Override
	public boolean canBeScheduledOn(Date start, Date stop) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TimeSlot getTimeTable() {
		// TODO Auto-generated method stub
		return null;
	}
}
