package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import be.kuleuven.cs.som.annotate.*;

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
	 BloodAnalysis(int amount,String focus) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super(45);
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