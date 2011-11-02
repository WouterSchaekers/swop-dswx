package medicaltest;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	// the name of the test is used to determine what kind of test it was later on.
	public static final String TESTNAME = "BloodAnalysis"; 
	public static final int DURATION = 45;
	private String focus = "";
	private int amountOfAnalyses = 0;
	
	/**
	 * Default constructor. Will call MedicalTest("BloodAnalysis")
	 */
	public BloodAnalysis(){
		super(TESTNAME, DURATION);
	}
	
	public void setFocus(String focus) {
		this.focus = focus;
	}
	
	public String getFocus() { 
		return this.focus;
	}
	
	public void setAmountOfAnalyses(int num) {
		this.amountOfAnalyses = num;
	}
	
	public int getAmountOfAnalyses() {
		return this.amountOfAnalyses;
	}

}