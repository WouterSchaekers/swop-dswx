package users;

import scheduler.HospitalDate;
import system.Location;

abstract class PreferenceState
{

	public abstract Location getLocationAt(HospitalDate date);

}
