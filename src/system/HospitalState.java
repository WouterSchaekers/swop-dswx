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
	public final TimeLord systemTime;
	public final UserManager userManager;
	public final MachinePool machinePool;
	public final PatientFileManager patientFileManager;
	public final TaskManager taskManager;
	public final Scheduler scheduler;
	public final Warehouse warehouse;
	

	/**
	 * Initializes an empty hospital
	 * @throws InvalidTimeLordException 
	 * @throws InvalidHospitalDateException 
	 */
	public HospitalState()  {
		try{
		this.systemTime = new TimeLord();
		this.userManager = new UserManager();
		this.machinePool = new MachinePool();
		this.patientFileManager = new PatientFileManager();
		this.scheduler = new Scheduler(this.systemTime);
		this.taskManager = new TaskManager(this.scheduler);
		this.warehouse=new Warehouse(systemTime.getSystemTime());
		}catch(Exception e)
		{
			throw new Error();
		}
	}

}
