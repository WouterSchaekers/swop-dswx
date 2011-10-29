package medicaltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest
{	
	// all available medical tests.
	private static Collection<MedicalTest> medicalTests = new ArrayList<MedicalTest>(Arrays.asList(new BloodAnalysis(),new UltraSoundScan(), new XRayScan()));

	// all childclasses will have their names be final and static and will use this var to store that information in.
	protected String testName = "";
	
	/**
	 * Default constructor.
	 * @param name
	 * The name of this medical test.
	 */
	public MedicalTest(String name) {
		this.testName = name;
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
