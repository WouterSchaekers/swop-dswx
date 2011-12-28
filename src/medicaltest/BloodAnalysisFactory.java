package medicaltest;

import scheduler.HospitalDate;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class BloodAnalysisFactory extends MedicalTestFactory
{
	private String focus;
	private int numberOfAnalysis;
	private long duration;
	/**
	 * Default constructor, only visible in the package since you have to create 
	 * the factories in the MedicalTestsClass
	 */
	BloodAnalysisFactory() {
		setDuration(45*HospitalDate.ONE_MINUTE);
	}

	/**
	 * Method to set the focus of the bloodanalysis
	 * 
	 * @param focus
	 * @throws IllegalArgumentException
	 *             if the provided argument is null
	 */
	public void setFocus(String focus) {
		if (!isValidFocus(focus))
			throw new IllegalArgumentException("invalid focus value");
		this.focus = focus;
	}

	/**
	 * Method to set the number of analysis that have to be executed
	 * 
	 * @param numberOfAnalysis
	 * @throws if
	 *             the provided number is invalid (<=0)
	 */
	public void setNumberOfAnalysis(int numberOfAnalysis) {
		if (!isValidNumberOfAnalysis(numberOfAnalysis))
			throw new IllegalArgumentException("Illegal amount of analysis");
		this.numberOfAnalysis = numberOfAnalysis;
	}

	@Override
	public MedicalTest create() throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		if (!ready())
			throw new IllegalArgumentException();

		return new BloodAnalysis(numberOfAnalysis, focus, duration);
	}

	/**
	 * Checks all the set fields in this factory.
	 * 
	 * @return false if one of the fields has not been inialized or is
	 *         wrongfully inialized.
	 */
	private boolean ready() {
		boolean rv = true;
		rv &= isValidDuration(duration);
		rv &= isValidFocus(focus);
		rv &= isValidNumberOfAnalysis(numberOfAnalysis);
		return rv;
	}

	/**
	 * Simple method to check if the provided focus argument is valid
	 * 
	 * @param focus
	 *            the focus of the bloodanalysis that will be created by this
	 *            factory
	 * @return true if the argumet is not null.
	 */
	private boolean isValidFocus(String focus) {
		return !(focus == null);
	}

	/**
	 * Method to check if the amount of analysis is valid ( greater then 0)
	 * 
	 * @param numberOfAnalysis
	 *            the number of analysis that have to be executed
	 * @return true if the argument is > 0
	 */
	private boolean isValidNumberOfAnalysis(int numberOfAnalysis) {
		return numberOfAnalysis > 0;
	}

	/**
	 * private setter to maybe use in future iteration
	 * 
	 * @param l
	 */
	private void setDuration(long l) {
		if (!isValidDuration(l))
			throw new IllegalArgumentException("wrong duration provided");
		this.duration = l;
	}

	/**
	 * Checks if the duration is valid
	 * 
	 * @param l
	 * @return
	 */
	private boolean isValidDuration(long l) {
		return l == 45*HospitalDate.ONE_MINUTE;
	}
}
