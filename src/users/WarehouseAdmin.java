package users;

import controllers.interfaces.WarehouseAdminIN;
import exceptions.InvalidNameException;

/**
 * This class represents the administrator of the warehouse that is associated
 * with a campus.
 */
public class WarehouseAdmin extends User implements WarehouseAdminIN
{
	public WarehouseAdmin(String name) throws InvalidNameException {
		super(name);
	}
}