package users;

import patient.PatientFileManager;

public abstract class User
{
	PatientFileManager patientm;
	
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
