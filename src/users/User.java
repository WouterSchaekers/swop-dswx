package users;

import patient.PatientFileManager;

public abstract class User
{
	PatientFileManager patientm;
	
	protected String title;
	protected User(String name, String title) {
		this.name = name;
		this.title = title;
	}


	protected String name;

	public String getName() {
		return name;
	}
	
	public String getTitle(){
		return title;
	}
}
