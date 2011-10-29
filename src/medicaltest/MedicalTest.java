package medicaltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import patient.PatientFile;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest
{	
	// all available medical tests.
	private static Collection<MedicalTest> medicalTests = new ArrayList<MedicalTest>(Arrays.asList(new BloodAnalysis(null),new UltraSoundScan(null), new XRayScan(null)));

	// all childclasses will have their names be final and static and will use this var to store that information in.
	private String testName = "";
	private PatientFile patientFile;
	
	/**
	 * Default constructor.
	 * @param name
	 * The name of this medical test.
	 */
	public MedicalTest(String name, PatientFile patientFile) {
		this.testName = name;
		this.patientFile = patientFile;
	}

	/**
	 * @return all available tests.
	 */
	public static Collection<MedicalTest> availableTests() {
		return medicalTests;
	}
 
	public String getTestName(){
		return this.testName;
	}
}
