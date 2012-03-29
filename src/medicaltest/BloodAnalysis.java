package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import machine.BloodAnalyser;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	/**
	 * amount of times an analysis has to be run
	 */
	private final int amount_;
	/**
	 * The focus of this bloodanalysis
	 */
	private final String focus_;

	BloodAnalysis(PatientFile patientFile, HospitalDate creationTime,
			int amount, String focus) throws InvalidAmountException,
			InvalidHospitalDateException {
		super(patientFile, 45 * HospitalDate.ONE_MINUTE, creationTime);
		this.amount_ = amount;
		this.focus_ = focus;
	}

	@Basic
	public int getAmount() {
		return amount_;
	}

	@Basic
	public String getFocus() {
		return this.focus_;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(
				this.patientFile_.getPatient(), false));
		requirements.add(new RequirementType<BloodAnalyser>(
				BloodAnalyser.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		
	}
}