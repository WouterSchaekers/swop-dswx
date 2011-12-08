package users;

import java.util.Date;
import scheduler.TimeSlot;
import scheduler.task.Schedulable;
import controllers.interfaces.DoctorIN;

public class Doctor extends User implements DoctorIN, Schedulable
{
	public Doctor(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Doctor;
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
