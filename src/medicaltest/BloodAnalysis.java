package medicaltest;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	// the name of the test is used to determine what kind of test it was later on.
	public static final String TESTNAME = "BloodAnalysis"; 
	public static final int DURATION = 45;

	private int amount;
	private String focus;
	
	/**
	 * Default constructor. Will call MedicalTest("BloodAnalysis")
	 */
	public BloodAnalysis(){
		super(TESTNAME, DURATION);
	}

	@Basic
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Basic
	public int getAmount() {
		return amount;
	}

	@Basic
	public void setFocus(String focus) {
		this.focus = focus;
	}

	public String getFocus() {
		String rv = "" + focus;
		return rv;
	}
	
}