package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;

public class XRayScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later
	// on.
	public static final String TESTNAME = "XRayScan";
	public static final int DURATION = 15;

	/**
	 * Default constructor. 
	 * @see MedicalTest("XRayScan",15,patientFile)
	 */
	public XRayScan() throws InvalidNameException, InvalidDurationException {
		super(TESTNAME, DURATION);
	}

}
