package controllers;

import patient.PatientFileManager;
import users.UserManager;

public class DataPasser
{
	private final UserManager userm;
	private final PatientFileManager pfm;
	public DataPasser(UserManager userm,PatientFileManager pfm)
	{
		this.userm=userm;
		this.pfm=pfm;
	}
	UserManager getUserManager(){return userm;}
	PatientFileManager getPatientFileManager(){return pfm;}
	
}
