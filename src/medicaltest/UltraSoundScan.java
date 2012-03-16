package medicaltest;

import scheduler.HospitalDate;
import exceptions.InvalidDurationException;

public class UltraSoundScan extends MedicalTest
{

	public final static long DURATION = 30 * HospitalDate.ONE_MINUTE;
	private final String scaninfo;
	private final boolean recordVid;
	private final boolean recordImages;

	UltraSoundScan(String scaninfo, boolean recordVid, boolean recordImages)
			throws InvalidDurationException {
		super(UltraSoundScan.DURATION);
		this.scaninfo = scaninfo;
		this.recordVid = recordVid;
		this.recordImages = recordImages;
	}

	public String getScaninfo() {
		return scaninfo;
	}

	public boolean hasVideoRecordingEnabled() {
		return recordVid;
	}

	public boolean hasImageRecordingEnabled() {
		return recordImages;
	}
	
	@Override
	public String appointmentInfo() {
		String rv = "";
		rv += "Ultra Sound Scan \n";
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
