package users;

import system.Location;
import exceptions.InvalidNameException;

/**
 * Object that can create a Doctor object, also represents the type of the user
 * 
 */
public class DoctorFactory extends UserFactory
{
	private String name_;
	private Location location_;

	public void setName(String name) {
		name_ = name;
	}

	public void setLocation(Location location) {
		location_ = location;
	}

	@Override
	User create() throws InvalidNameException {
		return new Doctor(name_, location_);
	}

	@Override
	UserFactory newInstance() {
		return new DoctorFactory();
	}

	@Override
	public String toTitle() {return "Doctor";
	}

}
