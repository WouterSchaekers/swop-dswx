package users;

import controllers.interfaces.LocationIN;
import system.Location;
import exceptions.InvalidLocationException;
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
	User create() throws InvalidNameException, InvalidLocationException {
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
	@Override
	public void setLocation(LocationIN name) {
		setLocation((Location)name);
	}
}
