package medicaltest;

import patient.PatientFile;
import scheduler.HospitalDate;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

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
	
	private boolean isValidSystemTime(HospitalDate currentSystemTime) {
		return currentSystemTime != null;
	}
	
	public abstract MedicalTest create() throws FactoryInstantiationException;
}