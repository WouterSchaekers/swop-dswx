package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScan extends MedicalTest
{
	// the name of the test, 
	// used to determine what kind of test it was later on.

	/**
	 * Default constructor. 
	 * @throws InvalidTimeSlotException 
	 * @see MedicalTest("UltraSoundScan")
	 */
	public UltraSoundScan(int duration) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super( duration);
	}

}
