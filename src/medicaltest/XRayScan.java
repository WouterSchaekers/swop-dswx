package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class XRayScan extends MedicalTest
{
	// the name of the test, used to determine what kind of test it was later
	// on.
	private final String bodypart;
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getZoomlevel() {
		return zoomlevel;
	}

	public void setZoomlevel(float zoomlevel) {
		this.zoomlevel = zoomlevel;
	}

	public String getBodypart() {
		return bodypart;
	}

	private int num;
	private float zoomlevel;

	/**
	 * Default constructor. 
	 * @param zoomlevel 
	 * @param num 
	 * @throws InvalidTimeSlotException 
	 * @see MedicalTest("XRayScan",15,patientFile)
	 */

	XRayScan(String bodypart, int num, float zoomlevel,int duration) throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException {
		super(duration);
		this.bodypart=bodypart;
		this.num=num;
		this.zoomlevel=zoomlevel;
	}

}
