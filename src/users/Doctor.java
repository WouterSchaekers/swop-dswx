package users;

import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class Doctor extends SchedulableUser implements DoctorIN
{
	public Doctor(String name) throws InvalidNameException, InvalidTimeSlotException {
		super(name);
	}
	
	@Override
	public UserType type() {
		return UserType.Doctor;
	}
}