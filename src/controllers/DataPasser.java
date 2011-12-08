package controllers;

import patient.PatientFileManager;
import scheduler.Scheduler;
import scheduler.*;
import users.UserManager;

/**
 * This class will serve as a data transmitter.
 */
public class DataPasser
{
	private final UserManager userm;
	private final Scheduler scheduler;
	private final PatientFileManager pfm;

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
			Scheduler scheduler) {
		this.userm = userm;
		this.pfm = pfm;
		this.scheduler = scheduler;
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

}
