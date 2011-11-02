package medicaltest;

public class UltraSoundScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later on.
	public static final String TESTNAME = "UltraSoundScan";
	public static final int DURATION = 30;
	private boolean recVid = false;
	private boolean recImg = false;
	private String focus = "";
	/**
	 * Default constructor. Will call MedicalTest("UltraSoundScan")
	 */
	public UltraSoundScan(){
		super(TESTNAME, DURATION);
	}
	
	public void setFocus(String focus) {
		this.focus = focus;
	}
	
	public String getFocus() { 
		return this.focus;
	}
	
	public void setVid(boolean vid) {
		this.recVid = vid;
	}
	
	public boolean getVid() {
		return this.recVid;
	}
	
	public void setImg(boolean img) {
		this.recImg = img;
	}
	
	public boolean getImg() {
		return this.recImg;
	}
	
}
