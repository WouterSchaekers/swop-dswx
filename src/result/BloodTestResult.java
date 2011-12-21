package result;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidReportException;

/**
 * This class represent the result of a blood test.
 */
public class BloodTestResult extends Result
{

	private int amountOfBlood, amountOfPlatelets, amountOfRed, amountOfWhite;

	/**
	 * Default constructor. Will initialise all fields.
	 * @param amountOfBlood
	 * The amount of blood withdrawn.
	 * @param amountOfPlatelets
	 * The amount of platelets found.
	 * @param amountOfRed
	 * The amount of red blood cells found.
	 * @param amountOfWhite
	 * The amount of white blood cells found.
	 * @throws InvalidReportException
	 * @throws InvalidAmountException
	 */
	public BloodTestResult(int amountOfBlood, int amountOfPlatelets, int amountOfRed, int amountOfWhite)
			throws InvalidReportException, InvalidAmountException {
		super("Bloodtest");
		if (!super.canhaveAsAmount(amountOfBlood) && super.canhaveAsAmount(amountOfPlatelets) && super.canhaveAsAmount(amountOfRed) && super.canhaveAsAmount(amountOfWhite))
			throw new InvalidAmountException(
					"Invalid amount given to BloodTestResult!");
		this.amountOfBlood = amountOfBlood;
		this.amountOfPlatelets = amountOfPlatelets;
		this.amountOfRed = amountOfRed;
		this.amountOfWhite = amountOfWhite;
		
	}
	
	@Basic
	public int getAmountOfBlood() {
		return this.amountOfBlood;
	}
	
	@Basic
	public int getAmountOfPlatelets() {
		return this.amountOfPlatelets;
	}
	
	@Basic
	public int getAmountOfRed() {
		return this.amountOfRed;
	}
	
	@Basic
	public int getAmountOfWhite() {
		return this.amountOfWhite;
	}
	
}
