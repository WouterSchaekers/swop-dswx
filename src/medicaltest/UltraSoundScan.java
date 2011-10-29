package medicaltest;

public class UltraSoundScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later on.
	public static final String TESTNAME = "UltraSoundScan";
	public UltraSoundScan(){
		super(TESTNAME);
	}
	
	@Override
	public String getTestName() {
		return TESTNAME;
	}
}
