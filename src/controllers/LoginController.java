package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import system.Hospital;
import ui.UserFilter;
import users.SchedulableUser;
import users.User;
import users.UserManager;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.HospitalIN;
import controllers.interfaces.NurseIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;

/**
 * This class will be used as a login controller. There will be a 1:1 relation
 * between the amount of logged in users and the amount of controllers. Each
 * logincontroller will remember what user they logged in.
 */
@controllers.PUBLICAPI
public class LoginController extends HospitalController
{
	private boolean loggedIn = false;
	private User user = null;

	@controllers.PUBLICAPI
	public LoginController(HospitalIN hospital) throws InvalidHospitalException {
		super((Hospital)hospital);
	}

	/**
	 * @return A collection of all users currently in the system.
	 */
	@controllers.PUBLICAPI
	public Collection<UserIN> getAllUsers() {
		UserManager um = hospital.getUserManager();
		ArrayList<UserIN> users = new ArrayList<UserIN>();
		for (UserIN u : um.getAllUsers())
			users.add(u);
		return users;
	}

	@controllers.PUBLICAPI
	public NurseIN getSpecificNurse(String name) {
		return UserFilter.SpecificNurseFilter(new LinkedList<UserIN>(hospital.getUserManager().getAllUsers()), name);
	}

	@controllers.PUBLICAPI
	public DoctorIN getSpecificDoctor(String name) {
		return UserFilter.SpecificDoctorFilter(new LinkedList<UserIN>(hospital.getUserManager().getAllUsers()),name);
	}

	/**
	 * This method will login the user of this logincontroller.
	 * 
	 * @return A string with TODOs.
	 * @throws IllegalArgumentException
	 *             if (!isValidUser((User) user))
	 */
	@controllers.PUBLICAPI
	public String logIn(UserIN user) throws IllegalArgumentException {
		if (!isValidUser((User) user))
			throw new IllegalArgumentException("The given user is null!");
		this.user = (User) user;
		loggedIn = true;
		
		String todos;
		if(this.getUser() instanceof SchedulableUser) {
			todos = "TODOs:\n-----\n\n";
			Collection<Task<TaskDescription>> t = hospital.getTaskManager().getUnfinishedTasksFrom((SchedulableUser) getUser());
			for (Task<?> task : t) {
				todos += task.toString();
			}
		} else 
			todos = "";
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
	@controllers.PUBLICAPI
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
	@controllers.PUBLICAPI
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