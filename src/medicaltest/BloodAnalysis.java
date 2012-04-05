package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
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
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	private final int amount_;
	private final String focus_;
	/**
	 * The duration of a BloodAnalysis.
	 */
	public final static long DURATION_ = 45 * HospitalDate.ONE_MINUTE;

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
		super(patientFile, BloodAnalysis.DURATION_, creationDate);
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
		return this.amount_;
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

	/**
	 * @return A BloodAnalysisResultFactory.
	 */
	@Override
	public ResultFactory get() {
		return new BloodAnalysisResultFactory();
	}

	/**
	 * Gives a result, based on the information of the factory.
	 * 
	 * @param resultFactory
	 *            The factory the result will be based on.
	 * @return The Result based on the ResultFactory.
	 * @throws InvalidResultException
	 *             The given factory is not a BloodAnalysisResultFactory.
	 * @throws FactoryInstantiationException
	 *             The Factory was not ready yet.
	 */
	@Override
	public Result give(ResultFactory resultFactory) throws InvalidResultException, FactoryInstantiationException {
		Result result = resultFactory.create();
		if (!validResult(result))
			throw new InvalidResultException("The given factory is not a BloodAnalysisResultFactory.");
		setResult(result);
		return result;
	}

	/**
	 * Checks whether the given result is valid or not.
	 * 
	 * @param result
	 *            The result that has to be checked.
	 * @return True if the result is not null and is a BloodAnalysisResult.
	 */
	private boolean validResult(Result result) {
		return result != null && result instanceof BloodAnalysisResult;
	}
}