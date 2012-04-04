package users;

import system.Location;
import controllers.interfaces.UserFactoryIN;
import controllers.interfaces.WarehouseAdminIN;
import exceptions.InvalidNameException;

/**
 * This class represents the administrator of the warehouse that is associated
 * with a campus.
 */
public class WarehouseAdmin extends User implements WarehouseAdminIN
{
	private Location myCampus_;
	public WarehouseAdmin(String name, Location campus) throws InvalidNameException {
		super(name);
		myCampus_ = campus;
	}
	
	public Location getLocation() {
		return this.myCampus_;
	}

	@Override
	public UserFactory getType() {
		return new WarehouseAdminFactory();
	}

	@Override
	public UserFactoryIN getTypeIN() {
		return getType();
	}
	
}