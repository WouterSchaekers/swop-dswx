package users;

import java.util.*;
import scheduler.task.Schedulable;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.*;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

public class UserManager
{
	/**
	 * We use a Map so interaction with the user in usecases can go more smoothly.
	 */
	private Map<String, User> users;

	/**
	 * Default constructor.
	 */
	public UserManager() {
		users = new HashMap<String, User>();
	}

	@Basic
	public Collection<User> getAllUsers() {
		return new ArrayList<User>(users.values());
	}

	/**
	 * This method will create a new Nurse and add it to this UserManager's
	 * database.
	 * 
	 * @param string
	 *            The name of the Nurse.
	 * @return The created Nurse.
	 * @throws UserAlreadyExistsException
	 * @throws InvalidNameException
	 */
	public Nurse createNurse(String string) throws UserAlreadyExistsException, InvalidNameException {
		Nurse newUser = new Nurse(string);
		AddUser(newUser);
		return newUser;
	}

	/**
	 * This method will create a new Doctor and add it to this UserManager's
	 * database.
	 * 
	 * @param string
	 *            The name of the Doctor.
	 * @return The created Doctor.
	 * @throws UserAlreadyExistsException
	 * @throws InvalidNameException
	 */
	public Doctor createDoctor(String name) throws UserAlreadyExistsException, InvalidNameException {
		Doctor newUser = new Doctor(name);
		AddUser(newUser);
		return newUser;
	}
	
	/**
	 * @return A collection of all Nurses, casted to Schedulable.
	 */
	public Collection<Schedulable> getAllNurses() {
		Collection<Schedulable> rv = new ArrayList<Schedulable>();
		for(User u : this.users.values()) {
			if (u.type().equals(UserType.Nurse))
				rv.add((Schedulable)u);
		}
		
		return rv;
	}
	
	@Basic
	public Collection<DoctorIN> getAllDoctors() {
		Collection<DoctorIN> rv = new ArrayList<DoctorIN>();
		for(User u : this.users.values()) {
			if (u.type().equals(UserType.Doctor))
				rv.add((DoctorIN)u);
		}
		return rv;
	}
	
	/**
	 * This method is used to add users to the system, ensures valid adding etc.
	 * 
	 * @param newUser
	 * @throws UserAlreadyExistsException
	 */
	private void AddUser(User newUser) throws UserAlreadyExistsException {
		if (users.containsKey(newUser) || newUser == null)
			throw new UserAlreadyExistsException(newUser.name);
		this.users.put(newUser.name, newUser);
	}
}
