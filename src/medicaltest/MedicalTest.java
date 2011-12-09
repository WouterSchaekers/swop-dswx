package medicaltest;

import exceptions.*;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest
{
	// all childclasses will have their names be final and static and will use
	// this var to store that information in.

	private final String TESTNAME;
	private final long DURATION;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of this medical test.
	 * @param duration
	 *            The duration of this medical test.
	 * @throws InvalidNameException
	 *             If(!isValidName(name))
	 * @throws InvalidDurationException
	 *             If(!isValidDuration(duration))
	 */
	public MedicalTest(String name, long duration) throws InvalidNameException,
			InvalidDurationException {
		
		if (!isValidName(name)) 
			throw new InvalidNameException("Invalid name in constructor of MedicalTest!");
		if (!isValidDuration(duration))
			throw new InvalidDurationException("The duration of a MedicalTest must be strictly positive!");
	
		this.TESTNAME = name;
		this.DURATION = duration;
	}

	@Basic
	public String getTestName() {
		return this.TESTNAME;
	}

	@Basic
	public long getDURATION() {
		return DURATION;
	}
	
	/**
	 * @return True if n is a valid name for this medical test.
	 */ 
	private boolean isValidName(String n) {
		return n != null && n != "";
	}
	
	/**
	 * @return True if d is a valid duration for this medical test.
	 */
	private boolean isValidDuration(long d) {
		return d > 0;
	}

}
