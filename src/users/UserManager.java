package users;

import java.util.LinkedList;
import be.kuleuven.cs.som.annotate.Basic;

public abstract class UserManager
{
	
	private LinkedList<User> _users;

	/**
	 * Default constructor.
	 */
	public UserManager() {
		_users = new LinkedList<User>();
	}

	@Basic
	protected LinkedList<User> getAllUsers() {
		return this._users;
	}

//	public void createAndAddNurse(String string) throws UserAlreadyExistsException,
//			InvalidNameException {
//		Nurse newUser = new Nurse(string);
//		this.addUser(newUser);
//	}

//	public void createAndAddWarehouseAdmin(String string,
//			Warehouse warehouse, StockProvider stockProvider,
//			TimeLord timelord)
//			throws UserAlreadyExistsException, InvalidNameException {
//		WarehouseAdmin newUser = new WarehouseAdmin(string, warehouse, stockProvider, timelord);
//		this.addUser(newUser);
//	}

//	public void createAndAddHospitalAdmin(String string)
//			throws UserAlreadyExistsException, InvalidNameException {
//		HospitalAdmin newUser = new HospitalAdmin(string);
//		this.addUser(newUser);
//	}
    
//	public void createAndAddDoctor(String name) throws UserAlreadyExistsException,
//			InvalidNameException {
//		Doctor newUser = new Doctor(name);
//		this.addUser(newUser);
//	}

//	/**
//	 * @return A collection of all Nurses, casted to Schedulable.
//	 */
//	public Collection<NurseIN> getAllNurses() {
//		ArrayList<UserIN> t = new ArrayList<UserIN>();
//		for (User u : _users)
//			t.add(u);
//		return UserFilter.NurseFilter(t);
//	}

//	@Basic
//	public Collection<DoctorIN> getAllDoctors() {
//		ArrayList<UserIN> t = new ArrayList<UserIN>();
//		for (User u : _users)
//			t.add(u);
//		return UserFilter.DoctorFilter(t);
//	}
	
//	public NurseIN getSpecificNurse(String name) {
//		ArrayList<UserIN> t = new ArrayList<UserIN>();
//		for (User u : _users)
//			t.add(u);
//		return UserFilter.SpecificNurseFilter(t, name);
//	}
	
//	public DoctorIN getSpecificDoctor(String name) {
//		ArrayList<UserIN> t = new ArrayList<UserIN>();
//		for (User u : _users)
//			t.add(u);
//		return UserFilter.SpecificDoctorFilter(t, name);
//	}

//	public WarehouseAdmin getWarehouseAdmin() {
//		WarehouseAdmin warehouseAdmin = null;
//		for (User u : _users)
//			if (u instanceof WarehouseAdmin)
//				warehouseAdmin = (WarehouseAdmin) u;
//		return warehouseAdmin;
//	}

//	/**
//	 * This method is used to add users to the system, ensures valid adding etc.
//	 * 
//	 * @param newUser
//	 * @throws UserAlreadyExistsException
//	 */
//	public void addUser(User newUser) throws UserAlreadyExistsException {
//		if (_users.contains(newUser))
//			throw new UserAlreadyExistsException(newUser.name);
//		this._users.add(newUser);
//	}

//	public void updateTimeTables(HospitalDate newDate) {
//		for (User user : _users) {
//			if (user instanceof Schedulable) {
//				((Schedulable) user).updateTimeTable(newDate);
//			}
//		}
//	}
}