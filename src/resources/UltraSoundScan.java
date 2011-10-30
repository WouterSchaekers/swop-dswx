package resources;

import patient.PatientFile;

public class UltraSoundScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later on.
	public static final String TESTNAME = "UltraSoundScan";
	public static final int DURATION = 30;
	
	/**
	 * Default constructor. Will call MedicalTest("UltraSoundScan")
	 */
	public UltraSoundScan(PatientFile patientFile){
		super(TESTNAME, DURATION, patientFile);
	}
	
}
