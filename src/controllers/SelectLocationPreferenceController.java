package controllers;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import system.Campus;
import system.Location;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPreferenceException;

public class SelectLocationPreferenceController extends NeedsLoginController
{
	public SelectLocationPreferenceController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}
	
	private LinkedList<Location[]> possiblePreferences = new LinkedList<Location[]>();

	/**
	 * @return All possible preferences a doctor could want.
	 */
	public LinkedList<String> getPossiblePreferences() {
		LinkedList<String> preferences = new LinkedList<String>();
		preferences.add("\n|* |   Campus 1   |   Campus 2   |");
		Collection<Campus> locs = hospital.getAllCampuses();
		int i = 0;
		for(Campus location : locs) {
			for(Campus alternateLocation : locs) {
				String next = "\n|" + ++i +" |" + location + "|" + alternateLocation +"|";
				possiblePreferences.add(new Location[]{location,alternateLocation});
				preferences.add(next);
			}
		}
		return preferences;
	}
	
	/**
	 * @return The current preference of this doctor.
	 */
	public String getCurrentPreference() {
		String rv = null;
		if(((Doctor) lc.getUser()).getCurrentPreference() == null)
			rv = "Back and forth";
		else 
			for(Location loc : ((Doctor) lc.getUser()).getCurrentPreference())
				rv += " " + loc.toString() +",";
		return rv;
	}

	/**
	 * Sets the preference of presence of this doctor to the option chosen by the user.
	 * @throws InvalidPreferenceException
	 */
	public void setNewPreference(int index) throws InvalidPreferenceException {
		((Doctor) lc.getUser()).changePreferenceToSelected(new LinkedList<Location>(Arrays.asList(possiblePreferences
				.get(index))));
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}