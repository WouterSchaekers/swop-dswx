package users;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.NurseIN;
import controllers.interfaces.UserIN;

public class UserFilter
{
	public static Collection<DoctorIN> DoctorFilter(Collection<UserIN> users) {
		ArrayList<DoctorIN> rv = new ArrayList<DoctorIN>();
		for (UserIN u : users)
			if (u instanceof DoctorIN)
				rv.add((DoctorIN) u);
		return rv;
	}

	public static Collection<NurseIN> NurseFilter(Collection<UserIN> users) {
		ArrayList<NurseIN> rv = new ArrayList<NurseIN>();
		for (UserIN u : users)
			if (u instanceof NurseIN)
				rv.add((NurseIN) u);
		return rv;
	}
	
	public static NurseIN SpecificNurseFilter(Collection<UserIN> users, String name) {
		for (UserIN u : users)
			if (u instanceof NurseIN && u.getName().equals(name))
				return (NurseIN) u;
		return null;
	}

	public static DoctorIN SpecificDoctorFilter(Collection<UserIN> users, String name) {
		for (UserIN u : users)
			if (u instanceof DoctorIN && u.getName().equals(name))
				return (DoctorIN) u;
		return null;
	}
}
