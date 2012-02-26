package system;

import controllers.interfaces.HospitalStateI;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidTimeLordException;
import machine.MachinePool;
import patient.PatientFileManager;
import scheduler.Scheduler;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import users.UserManager;
import warehouse.Warehouse;

public class HospitalState implements HospitalStateI
{
	private final UserManager userManager;
	private final MachinePool machinePool;
	private final PatientFileManager patientFileManager;
	private final TaskManager taskManager;
	private final Scheduler scheduler;
	private final Warehouse warehouse;
	private final TimeLord systemTime;

	public TimeLord getSystemTime() {
		return systemTime;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public MachinePool getMachinePool() {
		return machinePool;
	}

	public PatientFileManager getPatientFileManager() {
		return patientFileManager;
	}

	public TaskManager getTaskManager() {
		return taskManager;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	/**
	 * Initializes an empty hospital
	 * 
	 * @throws InvalidTimeLordException
	 * @throws InvalidHospitalDateException
	 */
	public HospitalState() {
		try {
			this.systemTime = new TimeLord();
			this.userManager = new UserManager();
			this.machinePool = new MachinePool();
			this.patientFileManager = new PatientFileManager();
			this.scheduler = new Scheduler(this.systemTime);
			this.taskManager = new TaskManager(this.scheduler);
			this.warehouse = new Warehouse(systemTime.getSystemTime());
		} catch (Exception e) {
			throw new Error();
		}
	}
	
	public HospitalState(TimeLord timeLord, UserManager userManager, MachinePool machinePool, PatientFileManager patientFileManager, Scheduler scheduler, TaskManager taskManager, Warehouse warehouse){
		this.systemTime = timeLord;
		this.userManager = userManager;
		this.machinePool = machinePool;
		this.patientFileManager = patientFileManager;
		this.scheduler = scheduler;
		this.taskManager = taskManager;
		this.warehouse = warehouse;
	}

}
