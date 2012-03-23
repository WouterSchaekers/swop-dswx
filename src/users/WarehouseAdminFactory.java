package users;

import exceptions.InvalidNameException;
import system.Location;

public class WarehouseAdminFactory extends UserFactory
{
	private String name_;
	public void setName(String name)
	{
		name_=name;
	}
	
	@Override
	 User create() throws InvalidNameException {
		return new WarehouseAdmin(name_);
	}

	@Override
	UserFactory newInstance() {
		return new WarehouseAdminFactory();
	}

}
