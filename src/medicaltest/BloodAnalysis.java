package medicaltest;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	private final int amount;
	private final String focus;

	/**
	 * Constructor called by the BloodAnalysisFactory
	 * @throws InvalidTimeSlotException 
	 */
	 BloodAnalysis(int amount,String focus,int duration) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super(duration);
		this.amount=amount;
		this.focus=focus;
	}

	

	@Basic
	public int getAmount() {
		return amount;
	}

	@Basic
	public String getFocus() {
		return this.focus;
	}
	
	
}