package users;

import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class Nurse extends SchedulableUser
{
	public Nurse(String name) throws InvalidNameException, InvalidTimeSlotException {
		super(name);
	}

	@Override
	public UserType type() {
		return UserType.Nurse;
	}
}