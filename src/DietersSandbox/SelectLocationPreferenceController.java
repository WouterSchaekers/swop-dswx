package DietersSandbox;

import java.util.Collection;
import system.CampusPreference;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class SelectLocationPreferenceController extends NeedsLoginController
{
	public SelectLocationPreferenceController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	public Collection<CampusPreference> getPossiblePreferences() {
		//TODO: fix
		return null;
	}
	
	public String getCurrentPreference() {
		return null;
		//TODO: implement
	}
	
	public void setNewPreference(String pref) {
		//TODO: implement
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}