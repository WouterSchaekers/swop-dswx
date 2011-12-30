package medicaltest;

import result.Result;
import scheduler.HospitalDate;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class XRayScan extends MedicalTest
{
	public final static long DURATION = 15 * HospitalDate.ONE_MINUTE;
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
	 * 
	 * @param zoomlevel
	 * @param num
	 * @throws InvalidTimeSlotException
	 * @see MedicalTest("XRayScan",15,patientFile)
	 */
	XRayScan(String bodypart, int num, float zoomlevel)
			throws InvalidNameException, InvalidDurationException,
			InvalidTimeSlotException {
		super(XRayScan.DURATION);
		this.bodypart = bodypart;
		this.num = num;
		this.zoomlevel = zoomlevel;
	}

	@Override
	public void setResult(Result r) {
		// TODO Auto-generated method stub
		
	}

}
