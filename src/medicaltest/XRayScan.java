package medicaltest;

public class XRayScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later on.
	public static final String TESTNAME = "XRayScan";
	public static final int DURATION = 15;
	private int amountOfImages = 0;
	private String bodypart = "";
	
	/**
	 * Default constructor. Will call MedicalTest("XRayScan",15,patientFile)
	 */
	public XRayScan(){
		super(TESTNAME, DURATION);
	}
	
	public String getBodypart() {
		return this.bodypart;
	}
	
	public void setBodyPart(String bodypart) {
		this.bodypart = bodypart;
	}
	
	public int getAmountOfImages() {
		return this.amountOfImages;
	}
	
	public void setAmountOfImages(int amount) {
		this.amountOfImages = amount;
	}
	
	
}
