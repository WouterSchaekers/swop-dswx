package controllers;

import patient.PatientFileManager;
import scheduler.Scheduler;
import users.UserManager;

public class DataPasser
{
	private final UserManager userm;
	private final Scheduler scheduler;
	private final PatientFileManager pfm;
	public DataPasser(UserManager userm,PatientFileManager pfm,Scheduler scheduler)
	{
		this.userm=userm;
		this.pfm=pfm;
		this.scheduler=scheduler;
	}
	UserManager getUserManager(){return userm;}
	PatientFileManager getPatientFileManager(){return pfm;}
	public Scheduler getScheduler() {
		// TODO Auto-generated method stub
		return scheduler;
	}
	
}
