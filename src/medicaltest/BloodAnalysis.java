package medicaltest;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	// the name of the test is used to determine what kind of test it was later on.
	public static final String TESTNAME = "BloodAnalysis"; 
	
	/**
	 * Default constructor.
	 */
	public BloodAnalysis(){
		super(TESTNAME);
	}
	
	@Override
	public String getTestName() {
		return TESTNAME;
	}
}