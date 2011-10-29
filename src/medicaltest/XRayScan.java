package medicaltest;

public class XRayScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later on.
	public static final String TESTNAME = "XRayScan";
	public XRayScan(){
		super(TESTNAME);
	}
	
	@Override
	public String getTestName() {
		return TESTNAME;
	}
}
