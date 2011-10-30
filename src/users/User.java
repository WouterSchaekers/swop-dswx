package users;

import patient.PatientFileManager;

public abstract class User
{
	public enum usertype{
		Doctor,Nurse,HospitalAdmin;
	}
	public abstract usertype type();
	PatientFileManager patientm;
	
	protected User(String name) {
		this.name = name;
	}


	protected String name;

	public String getName() {
		return name;
	}
}
