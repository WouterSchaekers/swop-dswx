package scheduler.tasks;

import patient.PatientFile;
import scheduler.HospitalDate;
import treatment.Medication;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public abstract class MedicationDescription extends TreatmentDescription
{
	private Medication medication_;
	
	public Medication getMedication(){
		return this.medication_;
	}
	
	public MedicationDescription(PatientFile patientFile, long duration, Medication medication, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, duration, creationTime);
		this.medication_ = medication;
	}
}