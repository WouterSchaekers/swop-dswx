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
	
	private boolean isValidSystemTime(HospitalDate currentSystemTime) {
		return currentSystemTime != null;
	}
	
	protected boolean isReady(){
		return isValidSystemTime(this.creationDate_) && isValidPatientFile(this.patientFile_);
	}
	
	public abstract MedicalTest create() throws FactoryInstantiationException;
}