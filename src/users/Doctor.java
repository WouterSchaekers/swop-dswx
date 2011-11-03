package users;

import java.util.ArrayList;
import scheduler.Appointment;

public class Doctor extends User
{
	public Doctor(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Doctor;
	}
}
