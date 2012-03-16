package medicaltest;

import scheduler.HospitalDate;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class XRayScan extends MedicalTest
{
	public final static long DURATION = 15 * HospitalDate.ONE_MINUTE;
	private final String bodypart;
	private int num;
	private float zoomlevel;

	XRayScan(String bodypart, int num, float zoomlevel)
			throws InvalidNameException, InvalidDurationException,
			InvalidTimeSlotException {
		super(XRayScan.DURATION);
		this.bodypart = bodypart;
		this.num = num;
		this.zoomlevel = zoomlevel;
	}
	
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
	
	@Override
	public String appointmentInfo() {
		String rv = "";
		rv += "Xray Scan\n";
		if (getScheduledTask() != null) {
			rv += "for \t:\t" + getScheduledTask().getPatient().getName()
					+ "\n";
			rv += "at \t:\t" + getScheduledTask().getTimeSlot().getStartPoint()
					+ "\ttill\t"
					+ getScheduledTask().getTimeSlot().getStopPoint();
		} else {
			rv += "Scan is not yet Scheduled";
		}
		return rv;
	}

}
