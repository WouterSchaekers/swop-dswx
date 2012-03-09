package system;

import machine.MachinePool;
import patient.PatientFileManager;
import scheduler.Scheduler;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import users.HospitalAdmin;
import users.UserManager;
import warehouse.Warehouse;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a hospital. It can be used to pass a bunch of data to
 * the controllers in a fashionable way.
 */
public class Hospital
{
	private final UserManager userManager;
	private final MachinePool machinePool;
	private final PatientFileManager patientFileManager;
	private final TaskManager taskManager;
	private final Scheduler scheduler;
	private final Warehouse warehouse;
	private final TimeLord systemTime;
	private final Campus campusA;
	private final Campus campusB;
	private final HospitalAdmin hospitalAdmin;

	/**
	 * Initializes an empty hospital with a hospital admin and 2 campusses.
	 */
	public Hospital() {
		try {
			this.systemTime = new TimeLord();
			this.userManager = new UserManager();
			this.machinePool = new MachinePool();
			this.patientFileManager = new PatientFileManager();
			this.scheduler = new Scheduler(this.systemTime);
			this.taskManager = new TaskManager(this.scheduler);
			this.warehouse = new Warehouse();
			this.campusA = new Campus();
			this.campusB = new Campus();
			this.hospitalAdmin = new HospitalAdmin("admin");
			this.userManager.addUser(this.hospitalAdmin);
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
	public Hospital(TimeLord timeLord, UserManager userManager,
			MachinePool machinePool, PatientFileManager patientFileManager,
			Scheduler scheduler, TaskManager taskManager, Warehouse warehouse,
			HospitalAdmin hospitalAdmin) {
		this.systemTime = timeLord;
		this.userManager = userManager;
		this.machinePool = machinePool;
		this.patientFileManager = patientFileManager;
		this.scheduler = scheduler;
		this.taskManager = taskManager;
		this.warehouse = warehouse;
		this.campusA = new Campus();
		this.campusB = new Campus();
		try {
			this.hospitalAdmin = new HospitalAdmin("admin");
			this.userManager.addUser(this.hospitalAdmin);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Error();
		}
		
	}

	@Basic
	public TimeLord getSystemTime() {
		return systemTime;
	}

	@Basic
	public UserManager getUserManager() {
		return userManager;
	}

	@Basic
	public MachinePool getMachinePool() {
		return machinePool;
	}

	@Basic
	public PatientFileManager getPatientFileManager() {
		return patientFileManager;
	}

	@Basic
	public TaskManager getTaskManager() {
		return taskManager;
	}

	@Basic
	public Scheduler getScheduler() {
		return scheduler;
	}

	@Basic
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	@Basic
	public Campus getCampusA() {
		return this.campusA;
	}
	
	@Basic
	public Campus getCampusB() {
		return this.campusB;
	}
	
	@Basic
	public HospitalAdmin getHospitalAdmin() {
		return this.hospitalAdmin;
	}
}