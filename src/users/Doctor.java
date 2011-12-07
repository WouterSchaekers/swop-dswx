package users;

import java.util.Date;
import task.Schedulable;
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
}
