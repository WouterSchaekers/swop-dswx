package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class XRayScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later
	// on.
	public static final String TESTNAME = "XRayScan";
	public static final int DURATION = 15;
	private final String bodypart;

	/**
	 * Default constructor. 
	 * @throws InvalidTimeSlotException 
	 * @see MedicalTest("XRayScan",15,patientFile)
	 */
	//TODO: reden om dit package only te maken zodat de factories gebruikt moeten worden!
	XRayScan(String bodypart) throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException {
		super(TESTNAME, DURATION);
		this.bodypart=bodypart;
	}

}
