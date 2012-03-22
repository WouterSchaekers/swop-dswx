package system;

import java.util.LinkedList;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import scheduler.Scheduler2;
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
	private UserManager userManager_;
	private PatientFileManager patientFileManager_;
	private TaskManager taskManager_;
	private TimeLord systemTime_;
	private Scheduler2 scheduler_;
	private LinkedList<Campus> campusses_;

	/**
	 * Initializes an empty hospital with a hospital admin and 2 campusses.
	 */
	public Hospital() {
		try {
			this.systemTime_ = new TimeLord();
			this.userManager_ = new UserManager();
			this.patientFileManager_ = new PatientFileManager();
			this.scheduler_ = new Scheduler2(this.systemTime_);
			this.taskManager_ = new TaskManager(this.scheduler_);
			this.campusses_ = new LinkedList<Campus>();
			//TODO: initilise campusses and give them warehouses.
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
			Scheduler2 scheduler, TaskManager taskManager,
			HospitalAdmin hospitalAdmin) {
		this.systemTime_ = timeLord;
		this.userManager_ = userManager;
		this.patientFileManager_ = patientFileManager;
		this.scheduler_ = scheduler;
		this.taskManager_ = taskManager;
		this.campusses_ = new LinkedList<Campus>();
	}

	@Basic
	public HospitalDate getSystemTime() {
		return systemTime_.getSystemTime();
	}

	@Basic
	public UserManager getUserManager() {
		return userManager_;
	}

	@Basic
	public PatientFileManager getPatientFileManager() {
		return patientFileManager_;
	}

	@Basic
	public TaskManager getTaskManager() {
		return taskManager_;
	}

	@Basic
	public Scheduler2 getScheduler() {
		return scheduler_;
	}
	
	@Basic
	public Campus getCampus(String name){
		for(Campus campus : campusses_){
			if(campus.getCampusName().equals(name)){
				return campus;
			}
		}
		throw new IllegalArgumentException("Campus does not exists");
	}
	
	public LinkedList<Campus> getAllCampusses() {
		return new LinkedList<Campus>(this.campusses_);
	}
	
	/**
	 * Adds a campus to this hospital.
	 */
	@Basic
	public void addCampus(String name) throws InvalidCampusException {
		if(name.equals(""))
			throw new InvalidCampusException("Invalid new campus name was given!");
		this.campusses_.add(new Campus("name", this));
	}
	
	/**
	 * Removes a campus from this hospital.
	 */
	public void removeCampus(String name) {
		LinkedList<Campus> newCP = new LinkedList<Campus>();
		for(Campus c : this.campusses_)
			if((c.getCampusName().equals(name)))
					newCP.add(c);
	}
}