package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScan extends MedicalTest
{
	// the name of the test, 
	// used to determine what kind of test it was later on.
	public static final String TESTNAME = "UltraSoundScan";
	public static final int DURATION = 30;

	/**
	 * Default constructor. 
	 * @throws InvalidTimeSlotException 
	 * @see MedicalTest("UltraSoundScan")
	 */
	public UltraSoundScan() throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super(TESTNAME, DURATION);
	}

}
