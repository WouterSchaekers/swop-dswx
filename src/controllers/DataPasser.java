package controllers;

import machine.MachinePool;
import patient.PatientFileManager;
import scheduler.Scheduler;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import users.UserManager;
import warehouse.Warehouse;

/**
 * This class will serve as a data transmitter.
 */
public class DataPasser
{
	private final UserManager userm;
	private final Scheduler scheduler;
	private final PatientFileManager pfm;
	private final MachinePool machinePool;
	private TimeLord theDoctor;
	private TaskManager taskmanager;
	private Warehouse warehouse;

	/**
	 * Default constructor.
	 * 
	 * @param userm
	 *            a usermanager.
	 * @param pfm
	 *            a patientfilemanager.
	 * @param scheduler
	 *            a scheduler
	 */
	public DataPasser(UserManager userm, PatientFileManager pfm,
			Scheduler scheduler,MachinePool machine,TaskManager taskmanager,TimeLord doctor,Warehouse warhouse) {
		this.userm = userm;
		this.pfm = pfm;
		this.scheduler = scheduler;
		this.machinePool=machine;
		this.theDoctor=doctor;
		this.taskmanager=taskmanager;
		this.theDoctor=doctor;
		this.warehouse=warhouse;
	}

	/**
	 * @return The usermanager stored here.
	 */
	UserManager getUserManager() {
		return userm;
	}

	/**
	 * @return The patientfilemanager stored here.
	 */
	PatientFileManager getPatientFileManager() {
		return pfm;
	}

	/**
	 * @return The scheduler stored here.
	 */
	Scheduler getScheduler() {
		return scheduler;
	}

	public MachinePool getMachinePool() {
		return machinePool;
	}

	public TimeLord getTimeLord() {
		return theDoctor;
	}

	 TaskManager getTaskmanager() {
		 return this.taskmanager;
	}

	public Warehouse getWareHouse() {
		return warehouse;
	}

}
