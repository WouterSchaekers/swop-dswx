package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScan extends MedicalTest
{
	public String getScaninfo() {
		return scaninfo;
	}

	public boolean hasVideoRecordingEnabled() {
		return recordVid;
	}

	public boolean hasImageRecordingEnabled() {
		return recordImages;
	}

	private final String scaninfo;
	private final boolean recordVid;
	private final boolean recordImages;


	/**
	 * Default constructor. 
	 * @param recordImages 
	 * @param recordVid 
	 * @param scaninfo 
	 * @throws InvalidTimeSlotException 
	 * @see MedicalTest("UltraSoundScan")
	 */
	UltraSoundScan(String scaninfo, boolean recordVid, boolean recordImages, int duration) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super( duration);
		this.scaninfo=scaninfo;
		this.recordVid=recordVid;
		this.recordImages=recordImages;
	}

}
