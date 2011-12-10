package users;

import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;

public class Doctor extends SchedulableUser implements DoctorIN
{
	public Doctor(String name) throws InvalidNameException {
		super(name);
	}
	
	@Override
	public UserType type() {
		return UserType.Doctor;
	}
}