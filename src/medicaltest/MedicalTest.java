package medicaltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
	private int duration;
	private PatientFile patientFile;
	private Date date;
	
	/**
	 * Default constructor.
	 * @param name
	 * The name of this medical test.
	 */
	public MedicalTest(String name, int duration, PatientFile patientFile) {
		this.testName = name;
		this.patientFile = patientFile;
		this.duration = duration;
	}

	/**
	 * @return all available tests.
	 */
	public static Collection<MedicalTest> availableTests() {
		return medicalTests;
	}
 
	/**
	 * @return The name of this medical test.
	 */
	public String getTestName(){
		return this.testName;
	}

	/**
	 * @return The date this test is scheduled to.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * This method can change the date a medical test needs to take place.
	 * @param date 
	 * The new date for this medical test.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return The patientfile to whom this medical test belongs to.
	 */
	public PatientFile getPatientFile() {
		return this.patientFile;
	}
	
	public int getDuration(){
		return duration;
	}
}
