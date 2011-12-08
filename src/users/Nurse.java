package users;

import java.util.Date;
import scheduler.TimeSlot;
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
	public void scheduleAt(TimeSlot t) {
		// TODO Auto-generated method stub
		
	}
}
