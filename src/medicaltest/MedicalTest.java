package medicaltest;

import java.util.ArrayList;
import java.util.Collection;

public abstract class MedicalTest
{
	private final String testName;
	public static Collection<MedicalTest> availableTests() {
		ArrayList<MedicalTest> medicalTests = new ArrayList<MedicalTest>();
		BloodAnalysis bloodAnalysis = new BloodAnalysis();
		UltraSoundScan ultraSoundScan = new UltraSoundScan();
		XRayScan xRayScan = new XRayScan();
		medicalTests.add(bloodAnalysis);
		medicalTests.add(bloodAnalysis);
		medicalTests.add(ultraSoundScan);
		return medicalTests;
	}
	public MedicalTest(String testName){
		this.testName = testName;
	}

}
