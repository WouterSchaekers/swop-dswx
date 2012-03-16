package system;

import java.util.LinkedList;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import scheduler.Scheduler;
import scheduler.TimeLord;
import scheduler.tasks.TaskManager;
import users.HospitalAdmin;
import users.UserManager;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidCampusException;

/**
 * This class represents a hospital. It can be used to pass a bunch of data to
 * the controllers in a fashionable way.
 */
public class Hospital implements Whereabouts
{
	private UserManager _usm;
	private PatientFileManager _pfm;
	private TaskManager _tm;
	private TimeLord _st;
	private Scheduler _sdl;
	private LinkedList<Campus> _cp;

	/**
	 * Initializes an empty hospital with a hospital admin and 2 campusses.
	 */
	public Hospital() {
		try {
			this._st = new TimeLord();
			this._usm = new UserManager();
			this._pfm = new PatientFileManager();
			this._sdl = new Scheduler(this._st);
			this._tm = new TaskManager(this._sdl);
			this._cp = new LinkedList<Campus>();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Error();
		}
	}

	/**
	 * Constructor that creates a new hospital with the given data as initial
	 * equipment.
	 * 
	 * @param timeLord
	 *            The TimeLord to be associated with this hospital.
	 * @param userManager
	 *            The UserManager that's associated with this hospital.
	 * @param machinePool
	 *            The MachinePool of this hospital.
	 * @param patientFileManager
	 *            The PatientFileManager for this hospital.
	 * @param scheduler
	 *            The Scheduler for this hospital.
	 * @param taskManager
	 *            The TaskManager for this hospital.
	 * @param warehouse
	 *            The Warehouse for this hospital.
	 * @throws  
	 */
	public Hospital(TimeLord timeLord, UserManager userManager, PatientFileManager patientFileManager,
			Scheduler scheduler, TaskManager taskManager,
			HospitalAdmin hospitalAdmin) {
		this._st = timeLord;
		this._usm = userManager;
		this._pfm = patientFileManager;
		this._sdl = scheduler;
		this._tm = taskManager;
		this._cp = new LinkedList<Campus>();
	}

	@Basic
	public HospitalDate getSystemTime() {
		return _st.getSystemTime();
	}

	@Basic
	public UserManager getUserManager() {
		return _usm;
	}

	@Basic
	public PatientFileManager getPatientFileManager() {
		return _pfm;
	}

	@Basic
	public TaskManager getTaskManager() {
		return _tm;
	}

	@Basic
	public Scheduler getScheduler() {
		return _sdl;
	}
	
	@Basic
	public Campus getCampus(String name){
		for(Campus campus : _cp){
			if(campus.getCampusName().equals(name)){
				return campus;
			}
		}
		throw new IllegalArgumentException("Campus does not exists");
	}
	
	public LinkedList<Campus> getAllCampusses() {
		return new LinkedList<Campus>(this._cp);
	}
	
	/**
	 * Adds a campus to this hospital.
	 */
	@Basic
	public void addCampus(String name) throws InvalidCampusException {
		if(name.equals(""))
			throw new InvalidCampusException("Invalid new campus name was given!");
		this._cp.add(new Campus("name", this));
	}
	
	/**
	 * Removes a campus from this hospital.
	 */
	public void removeCampus(String name) {
		LinkedList<Campus> newCP = new LinkedList<Campus>();
		for(Campus c : this._cp)
			if((c.getCampusName().equals(name)))
					newCP.add(c);
	}
}