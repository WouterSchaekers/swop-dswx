package users;

import java.util.*;
import scheduler.HospitalDate;
import scheduler.task.Schedulable;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.NurseIN;
import controllers.interfaces.UserIN;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class UserManager
{
	/**
	 * We use a Map so interaction with the user in usecases can go more smoothly.
	 */
	private Collection<User> users;

	/**
	 * Default constructor.
	 */
	public UserManager() {
		users = new ArrayList<User>();
	}

	@Basic
	public Collection<User> getAllUsers() {
		return new ArrayList<User>(users);
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
	 * @throws InvalidTimeSlotException 
	 */
	public Nurse createNurse(String string) throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException {
		Nurse newUser = new Nurse(string);
		AddUser(newUser);
		return newUser;
	}
	/**
	 * This method will create a new HospitalAdmin and add it to this UserManager's
	 * database.
	 * 
	 * @param string
	 *            The name of the Nurse.
	 * @return The created Nurse.
	 * @throws UserAlreadyExistsException
	 * @throws InvalidNameException
	 * @throws InvalidTimeSlotException 
	 */
	public HospitalAdmin createHospitalAdmin(String string) throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException {
		HospitalAdmin newUser = new HospitalAdmin(string);
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
	 * @throws InvalidTimeSlotException 
	 */
	public Doctor createDoctor(String name) throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException {
		Doctor newUser = new Doctor(name);
		AddUser(newUser);
		return newUser;
	}
	
	/**
	 * @return A collection of all Nurses, casted to Schedulable.
	 */
	public Collection<NurseIN> getAllNurses() {
		ArrayList<UserIN> t = new ArrayList<UserIN>();
		for(User u:users)
			t.add(u);
		return UserFilter.NurseFilter(t);
	}
	
	@Basic
	public Collection<DoctorIN> getAllDoctors() {
		ArrayList<UserIN> t = new ArrayList<UserIN>();
		for(User u:users)
			t.add(u);
		return UserFilter.DoctorFilter(t);
	}
	
	/**
	 * This method is used to add users to the system, ensures valid adding etc.
	 * 
	 * @param newUser
	 * @throws UserAlreadyExistsException
	 */
	private void AddUser(User newUser) throws UserAlreadyExistsException {
		if (users.contains(newUser))
			throw new UserAlreadyExistsException(newUser.name);
		this.users.add(newUser);
	}
	
	public void updateTimeTables(HospitalDate newDate){
		for(User user : users){
			if(user instanceof Schedulable){
				((Schedulable)user).updateTimeTable(newDate);
			}
		}
	}
}