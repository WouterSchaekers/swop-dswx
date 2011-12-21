package users;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.NurseIN;
import controllers.interfaces.UserIN;

public class UserFilter
{
	public static Collection<DoctorIN> DoctorFilter(Collection<UserIN> users)
	{
		ArrayList<DoctorIN> rv= new ArrayList<DoctorIN>();
		for(UserIN u:users)
			if(u instanceof DoctorIN)
				rv.add((DoctorIN)u);
		return rv;
	}
	public static Collection<NurseIN> NurseFilter(Collection<UserIN> users)
	{
		ArrayList<NurseIN> rv= new ArrayList<NurseIN>();
		for(UserIN u:users)
			if(u instanceof NurseIN)
				rv.add((NurseIN)u);
		return rv;
	}
	
}
