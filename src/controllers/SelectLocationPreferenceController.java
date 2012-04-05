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

/**
 * Use this controller to select a locational preferences.
 */
public class SelectLocationPreferenceController extends NeedsLoginController
{
	/**
	 * A field to improve legiblity of the code.
	 */
	private LinkedList<Location[]> possiblePreferences = new LinkedList<Location[]>();

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The login controller of the user that wants to select their
	 *            locational preferences.
	 * @throws InvalidLoginControllerException
	 *             If the user stored in the given login controller is not a
	 *             doctor or if the given login controller is invalid in any
	 *             other way.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	public SelectLocationPreferenceController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * @return All possible preferences a doctor could want.
	 */
	public LinkedList<String> getPossiblePreferences() {
		LinkedList<String> preferences = new LinkedList<String>();
		preferences.add("\n|* |   Campus 1   |   Campus 2   |");
		Collection<Campus> locs = hospital.getAllCampuses();
		int i = 0;
		for (Campus location : locs) {
			for (Campus alternateLocation : locs) {
				String next = "\n|" + ++i + " |" + location + "|" + alternateLocation + "|";
				possiblePreferences.add(new Location[] { location, alternateLocation });
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
		if (((Doctor) loginController_.getUser()).getCurrentPreference() == null)
			rv = "Back and forth";
		else
			for (Location loc : ((Doctor) loginController_.getUser()).getCurrentPreference())
				rv += " " + loc.toString() + ",";
		return rv;
	}

	/**
	 * Changes the locational preferences of the doctor to the given preference.
	 * 
	 * @throws InvalidPreferenceException
	 *             If the given index is invalid.
	 */
	public void setNewPreference(int index) throws InvalidPreferenceException {
		((Doctor) loginController_.getUser()).changePreferenceToSelected(new LinkedList<Location>(Arrays
				.asList(possiblePreferences.get(index))));
	}

	/**
	 * @return True if the given user is a doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}