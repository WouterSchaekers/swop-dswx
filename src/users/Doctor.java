package users;

import controllers.interfaces.DoctorIN;

public class Doctor extends User implements DoctorIN
{
	public Doctor(String name) {
		super(name);
	}

	@Override
	public usertype type() {
		return usertype.Doctor;
	}

}
