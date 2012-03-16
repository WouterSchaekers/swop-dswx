package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import machine.BloodAnalyser;
import medicaltest.BloodAnalysis;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.ResourceRequirement;
import scheduler.requirements.SpecificResourceRequirement;
import users.Nurse;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class BloodAnalysisDescription extends MedicalTestDescription
{
	public BloodAnalysisDescription(PatientFile patientFile,
			long extraTime, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, BloodAnalysis.DURATION, creationTime);
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificResourceRequirement(this.patientFile_.getPatient()));
		requirements.add(new ResourceRequirement<Nurse>(Nurse.class));
		requirements.add(new ResourceRequirement<BloodAnalyser>(BloodAnalyser.class));
		return requirements;
	}
}