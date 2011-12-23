package system;

import machine.MachinePool;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import scheduler.Scheduler;
import scheduler.task.TaskManager;
import users.UserManager;

public class HospitalState
{
	public final TimeLord systemTime;
	public final UserManager userManager;
	public final MachinePool machinePool;
	public final PatientFileManager patientFileManager;
	public final TaskManager taskManager;
	public final Scheduler scheduler;
	

	/**
	 * Initializes an empty hospital
	 */
	public HospitalState() {
		this.systemTime = new TimeLord();
		this.userManager = new UserManager();
		this.machinePool = new MachinePool();
		this.patientFileManager = new PatientFileManager();
		this.taskManager = new TaskManager();
		this.scheduler = new Scheduler(this.systemTime);
	}

}
