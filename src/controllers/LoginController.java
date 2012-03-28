package controllers;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.tasks.ScheduledTask;
import system.Hospital;
import users.User;
import users.UserFilter;
import users.UserManager;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.NurseIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;

/**
 * This class will be used as a login controller. There will be a 1:1 relation
 * between the amount of logged in users and the amount of controllers. Each
 * logincontroller will remember what user they logged in.
 */
public class LoginController extends HospitalController
{
	private boolean loggedIn = false;
	private User user = null;

	public LoginController(Hospital hospital) throws InvalidHospitalException {
		super(hospital);
	}

	/**
	 * @return A collection of all users currently in the system.
	 */
	public Collection<UserIN> getAllUsers() {
		UserManager um = hospital.getUserManager();
		ArrayList<UserIN> users = new ArrayList<UserIN>();
		for (UserIN u : um.getAllUsers())
			users.add(u);
		return users;
	}
	
	public NurseIN getSpecificNurse(String name) {
		return UserFilter.SpecificNurseFilter(hospital.getUserManager().getAllUsers(), name);
	}
	
	public DoctorIN getSpecificDoctor(String name) {
		return UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUsers(), name);
	}

	/**
	 * This method will login the user of this logincontroller.
	 * 
	 * @return A string with TODOs.
	 * @throws IllegalArgumentException
	 *             if (!isValidUser((User) user))
	 */
	public String logIn(UserIN user) throws IllegalArgumentException {
		if (!isValidUser((User) user))
			throw new IllegalArgumentException("The given user is null!");
		this.user = (User) user;
		loggedIn = true;
		
		String todos = "TODOs:\n-----\n\n";
		Collection<ScheduledTask<?>> colSched = hospital.getTaskManager().getScheduledTasks();
		for(ScheduledTask<?> task : colSched) {
			if(task.getUsedResources().contains(getUser()))
				todos += " * " + task.toString() + "\n";
		}
		return todos;
	}

	/**
	 * This method will check if the given user is a valid user for this
	 * logincontroller.
	 * 
	 * @param u
	 *            The user.
	 * @return True if u != null;
	 */
	private boolean isValidUser(User u) {
		return !(u == null);
	}

	/**
	 * @return True if the user of this controller is logged in.
	 */
	public boolean loggedIn() {
		return loggedIn;
	}

	/**
	 * @return The user of this logincontroller.
	 */
	User getUser() {
		return this.user;
	}

	/**
	 * @return The user whom has been granted log on access.
	 */
	public UserIN getUserIN() {
		return user;
	}

	/**
	 * Equality test
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LoginController))
			return false;
		LoginController that = (LoginController) o;
		return this.loggedIn == that.loggedIn & this.user.equals(that.user);
	}
}