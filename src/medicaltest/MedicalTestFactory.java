package medicaltest;

import patient.PatientFile;
import scheduler.HospitalDate;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidHospitalDateException;

public abstract class MedicalTestFactory
{
	protected PatientFile patientFile_;
	protected HospitalDate creationDate_;
	
	public void setPatientFile(PatientFile patientFile){
		if(! isValidPatientFile(patientFile))
			throw new IllegalArgumentException("Invalid patient file given to medical test factory!");
		this.patientFile_ = patientFile;
	}
	
	public void setCreationDate(HospitalDate creationDate) throws InvalidHospitalDateException{
		if (!isValidSystemTime(creationDate))
			throw new InvalidHospitalDateException(
					"Invalid creationTime given to Unscheduled Task");
		this.creationDate_ = creationDate;
	}
	
	private boolean isValidPatientFile(PatientFile patientFile){
		return patientFile != null;
	}
	
	private boolean isValidSystemTime(HospitalDate time) {
		return time != null;
	}
	
	protected boolean isReady() {
		return this.isValidPatientFile(patientFile_) && this.isValidSystemTime(creationDate_);
	}
	/**
	 * DO NOT CALL THIS METHOD!!!!
	 *GIVE THE FACTORY TO A PATIENTFILE!!
	 */
	@Deprecated
	public abstract MedicalTest create() throws FactoryInstantiationException;
}