package medicaltest;

import result.Result;
import scheduler.HospitalDate;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	/**
	 * amount of times an analysis has to be run
	 */
	private final int amount;
	/**
	 * The focus of this bloodanalysis
	 */
	private final String focus;

	public final static long DURATION = 45 * HospitalDate.ONE_MINUTE;

	/**
	 * Constructor called by the BloodAnalysisFactory
	 * 
	 * @throws InvalidTimeSlotException
	 */
	BloodAnalysis(int amount, String focus) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super(BloodAnalysis.DURATION);
		this.amount = amount;
		this.focus = focus;
	}

	@Basic
	public int getAmount() {
		return amount;
	}

	@Basic
	public String getFocus() {
		return this.focus;
	}

	@Override
	public void setResult(Result r) {
		// TODO Auto-generated method stub
		
	}

}