package users;

import java.util.Collection;
import java.util.LinkedList;

public class Users
{
	public static Collection<UserFactory> factories() {
		Collection<UserFactory> rv = new LinkedList<UserFactory>();
		rv.add(new NurseFactory());
		rv.add(new DoctorFactory());
		rv.add(new WarehouseAdminFactory());
		return rv;
	}

}
