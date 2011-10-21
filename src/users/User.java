package users;

import patient.PatientManager;

public abstract class User
{
	PatientManager patientm;
	
	protected String title;
	protected User(String name) {
		this.name = name;
	}


	protected String name;

	public String getName() {
		return name;
	}

	protected int ssid;

	public int getSsid() {
		return ssid;
	}
}
