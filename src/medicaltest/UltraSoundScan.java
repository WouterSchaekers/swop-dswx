package medicaltest;

import patient.PatientFile;

public class UltraSoundScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later on.
	public static final String TESTNAME = "UltraSoundScan";
	
	/**
	 * Default constructor. Will call MedicalTest("UltraSoundScan")
	 */
	public UltraSoundScan(PatientFile patientFile){
		super(TESTNAME, patientFile);
	}
	
}
