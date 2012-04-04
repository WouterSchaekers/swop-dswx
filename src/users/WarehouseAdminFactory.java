package users;

import system.Location;
import exceptions.InvalidNameException;

public class WarehouseAdminFactory extends UserFactory
{
	private String name_;
	private Location location_;

	public void setName(String name) {
		name_ = name;
	}

	public void setLocation(Location loc) {
		location_ = loc;
	}

	@Override
	User create() throws InvalidNameException {
		return new WarehouseAdmin(name_, location_);
	}

	@Override
	UserFactory newInstance() {
		return new WarehouseAdminFactory();
	}

	@Override
	public String toTitle() {
		return "Warehouse admin";
	}

}
