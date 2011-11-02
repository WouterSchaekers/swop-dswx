package controllers;

import patient.PatientFile;

public class DTOPatientFile
{
	private PatientFile file;
	private String name;
	public DTOPatientFile(PatientFile file)
	{
		this.file=file;
		this.name=file.getName();
	}
	public String name()
	{
		return name;
	}
	PatientFile getPatientFile(){
		return file;
	}
	public String getName() {
		return name();
	}
	public boolean isDischarged() {
		return file.isDischarged();
	}
}
