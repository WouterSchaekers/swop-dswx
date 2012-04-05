package medicaltest;

import exceptions.FactoryInstantiationException;

/**
 * A BloodAnalysisFactory is a factory, used to create a Blood Analysis.
 */
public class BloodAnalysisFactory extends MedicalTestFactory
{
	private String focus_;
	private int numberOfAnalysis_;

	/**
	 * Default constructor.
	 */
	public BloodAnalysisFactory() {
		;
	}

	/**
	 * Method to set the focus of the bloodanalysis
	 * 
	 * @param focus
	 *            The focus of the blood analysis.
	 */
	public void setFocus(String focus) {
		this.focus_ = focus;
	}

	/**
	 * Method to set the number of analysis that have to be executed
	 * 
	 * @param numberOfAnalysis
	 *            The number of analyses.
	 */
	public void setNumberOfAnalysis(int numberOfAnalysis) {
		this.numberOfAnalysis_ = numberOfAnalysis;
	}

	/**
	 * Creates a Blood Analysis built from the given information.
	 * 
	 * @return A Blood Analysis built from the given information.
	 * @throws FactoryInstantiationException
	 *             The factory was not ready yet.
	 */
	@Override
	public MedicalTest create() throws FactoryInstantiationException {
		if (!ready())
			throw new FactoryInstantiationException("BloodAnalysisFactory is not properly instantiated yet.");
		return new BloodAnalysis(this.patientFile_, this.creationDate_, this.numberOfAnalysis_, this.focus_);
	}

	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the focus and the numberOfAnalysis is valid.
	 */
	private boolean ready() {
		return super.isReady() && isValidFocus() && isValidNumberOfAnalysis();
	}

	/**
	 * Checks whether the focus is valid.
	 * 
	 * @return True if the focus is not null and not empty.
	 */
	private boolean isValidFocus() {
		return this.focus_ != null && !this.focus_.isEmpty();
	}

	/**
	 * Checks whether the number of analysis is valid.
	 * 
	 * @return True if the number of analysis is strickt positive.
	 */
	private boolean isValidNumberOfAnalysis() {
		return this.numberOfAnalysis_ > 0;
	}

	/**
	 * Returns a new instance of the current factory.
	 * 
	 * @return A new instance of the current factory.
	 */
	@Override
	public MedicalTestFactory newInstance() {
		return new BloodAnalysisFactory();
	}
}