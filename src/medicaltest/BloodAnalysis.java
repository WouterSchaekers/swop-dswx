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
	// the name of the test is used to determine what kind of test it was later
	// on.
	private int amount;
	private String focus;

	/**
	 * Default constructor. Will call MedicalTest("BloodAnalysis")
	 * @throws InvalidTimeSlotException 
	 */
	public BloodAnalysis() throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super(45);
	}

	@Basic
	public void setAmount(int amount) {
		if(!canHaveAsAmount(amount))
			throw new IllegalArgumentException("Invalid amount given to bloodanalysis!");
		this.amount = amount;
	}

	@Basic
	public int getAmount() {
		return amount;
	}

	@Basic
	public void setFocus(String focus) {
		if(!canHaveAsFocus(focus))
			throw new IllegalArgumentException("Invalid focus given to setFocus in bloodanalysis!");
		this.focus = focus;
	}

	@Basic
	public String getFocus() {
		return this.focus;
	}
	
	/**
	 * @return True if amount is a valid amount for this bloodanalysis
	 */
	private boolean canHaveAsAmount(int amount) {
		return amount > 0;
	}
	
	/**
	 * @return True if focus is a valid focus for this bloodtest
	 */
	private boolean canHaveAsFocus(String focus) {
		return !focus.equals("");
	}
	
}