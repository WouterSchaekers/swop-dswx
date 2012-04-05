package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;
import machine.BloodAnalyser;
import patient.PatientFile;
import result.BloodAnalysisResult;
import result.BloodAnalysisResultFactory;
import result.Result;
import result.ResultFactory;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	private final int amount_;
	private final String focus_;

	/**
	 * The default constructor. Package visible since it should only be used by
	 * the factories.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param amount
	 *            The number of analyses.
	 * @param focus
	 *            The focus of this analysis.
	 */
	BloodAnalysis(PatientFile patientFile, HospitalDate creationDate, int amount, String focus) {
		super(patientFile, 45 * HospitalDate.ONE_MINUTE, creationDate);
		this.amount_ = amount;
		this.focus_ = focus;
	}

	/**
	 * Returns the number of analyses.
	 * 
	 * @return The number of analyses.
	 */
	@Basic
	public int getAmount() {
		return amount_;
	}

	/**
	 * Returns the focus of the analysis.
	 * 
	 * @return The focus of the analysis.
	 */
	@Basic
	public String getFocus() {
		return this.focus_;
	}

	/**
	 * Returns all the requirements that are needed to forfill this task.
	 * 
	 * @return All the requirements that are needed to forfill this task.
	 */
	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new RequirementType<BloodAnalyser>(BloodAnalyser.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}

	@Override
	public ResultFactory get() {
		return new BloodAnalysisResultFactory();
	}

	@Override
	public Result give(ResultFactory builder) throws InvalidResultException, FactoryInstantiationException {
		Result myresult = builder.create();
		if(!validResult(myresult))
			throw new InvalidResultException("invalid result");
		setResult(myresult);
		return myresult;
	}

	private boolean validResult(Result myresult) {
		return myresult!=null&&myresult instanceof BloodAnalysisResult;
	}
}