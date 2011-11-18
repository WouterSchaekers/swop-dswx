package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest
{
	// all childclasses will have their names be final and static and will use
	// this var to store that information in.

	private final String TESTNAME;
	private final int DURATION;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of this medical test.
	 */
	public MedicalTest(String name, int duration) throws InvalidNameException, InvalidDurationException{
		if(name == null || name == ""){
			throw new InvalidNameException("Name is not set or empty");
		}
		if(duration <= 0){
			throw new InvalidDurationException("The duration must be strickt positive.");
		}
		this.TESTNAME = name;
		this.DURATION = duration;
	}

	@Basic
	public String getTestName() {
		return this.TESTNAME;
	}

	public int getDURATION() {
		return DURATION;
	}

}
