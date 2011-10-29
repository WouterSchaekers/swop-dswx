package medicaltest;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest
{	
	// all available medical tests.
	private Collection<MedicalTest> medicalTests;

	// all childclasses will have their names be final and static and will use this var to store that information in.
	protected String testName = "";
	
	/**
	 * Default constructor.
	 * Will initialise all available medical tests.
	 * @param name
	 * The name of this medical test.
	 */
	public MedicalTest(String name) {
		this.testName = name;
		medicalTests = new ArrayList<MedicalTest>();
		
		BloodAnalysis bloodAnalysis = new BloodAnalysis();
		UltraSoundScan ultraSoundScan = new UltraSoundScan();
		XRayScan xRayScan = new XRayScan();
		
		medicalTests.add(bloodAnalysis);
		medicalTests.add(ultraSoundScan);
		medicalTests.add(xRayScan);
	}

	/**
	 * @return all available tests.
	 */
	public Collection<MedicalTest> availableTests() {
		return this.medicalTests;
	}
 
	public String getTestName(){
		return this.testName;
	}
}
