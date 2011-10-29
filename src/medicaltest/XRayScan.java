package medicaltest;

import patient.PatientFile;

public class XRayScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later on.
	public static final String TESTNAME = "XRayScan";
	public static final int DURATION = 15;
	
	/**
	 * Default constructor. Will call MedicalTest("XRayScan")
	 */
	public XRayScan(PatientFile patientFile){
		super(TESTNAME, DURATION, patientFile);
	}
	
}
