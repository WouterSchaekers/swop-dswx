package medicaltest;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest
{	
	// all childclasses will have their names be final and static and will use this var to store that information in.
	private String testName = "";
	
	/**
	 * Default constructor.
	 * @param name
	 * The name of this medical test.
	 */
	public MedicalTest(String name, int duration) {
		this.testName = name;		
	}
 
	/**
	 * @return The name of this medical test.
	 */
	public String getTestName(){
		return this.testName;
	}
	
}
