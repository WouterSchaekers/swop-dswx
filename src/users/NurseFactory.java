package users;

import system.Location;
import exceptions.InvalidNameException;

public class NurseFactory extends UserFactory
{

	private String name_;
	public void setName(String name_) {
		this.name_ = name_;
	}

	public void setLocation(Location location_) {
		this.location_ = location_;
	}

	private Location location_;

	@Override
	User create() throws InvalidNameException {
		return new Nurse(name_,location_);
	}

	@Override
	UserFactory newInstance() {
		return new NurseFactory();
	}

	@Override
	public String toTitle() {
		return "Nurse";
	}

}
