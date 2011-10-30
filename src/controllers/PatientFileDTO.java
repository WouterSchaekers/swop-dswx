package controllers;

import patient.PatientFile;

public class PatientFileDTO
{
	private PatientFile file;
	private String name;
	public PatientFileDTO(PatientFile file)
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
}
