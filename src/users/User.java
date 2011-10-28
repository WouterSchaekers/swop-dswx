package users;

import patient.PatientFileManager;

public abstract class User
{
	PatientFileManager patientm;
	
	protected User(String name) {
		this.name = name;
	}


	protected String name;

	public String getName() {
		return name;
	}
}
