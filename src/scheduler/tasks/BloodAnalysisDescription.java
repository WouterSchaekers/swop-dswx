package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import machine.BloodAnalyser;
import medicaltest.BloodAnalysis;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
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
	public Collection<Requirement> getAllOtherRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<BloodAnalyser>(BloodAnalyser.class));
		return requirements;
	}

	@Override
	public Requirement getExecutor() {
		return new RequirementType<Nurse>(Nurse.class);
	}
}