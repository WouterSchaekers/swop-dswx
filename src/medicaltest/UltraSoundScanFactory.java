package medicaltest;

import scheduler.HospitalDate;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents something that can create UltraSoundScans.
 */
public class UltraSoundScanFactory extends MedicalTestFactory
{
	private String scaninfo;
	private boolean recordVidSet=false;
	private boolean recordImageSet=false;
	private boolean recordVid;
	private boolean recordImages;
	UltraSoundScanFactory(){}
	/**
	 * Sets if there should be a video recoded
	 * @param recordVid
	 */
	public void setRecordVid(boolean recordVid) {
		this.recordVidSet=true;
		this.recordVid = recordVid;
	}
	public void setRecordImages(boolean recordImages) {
		this.recordImageSet=true;
		this.recordImages = recordImages;
	}
	/**
	 * Sets the scan info
	 * @param scaninfo
	 * @throws IllegalArgumentException
	 * 	if null was given as an argument
	 */
	public void setScanInfo(String scaninfo) throws IllegalArgumentException
	{
		if(!isValidScanInfo(scaninfo))
			throw new IllegalArgumentException("invalid scan info");
		this.scaninfo=scaninfo;
	}
	@Override
	public MedicalTest create() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException {
		if(!ready())
			throw new IllegalArgumentException();
		return new UltraSoundScan(scaninfo,recordVid,recordImages);
	}
	
	@Override
	public String toString()
	{
		return "ultrasound scan";
	}
	/**
	 * checks if the ultrasound scan object is ready to be created.
	 * @return
	 */
	private boolean ready() {
		boolean rv= true;
		rv	&=recordImageSet;
		rv	&=recordVidSet;
		rv  &=isValidScanInfo(scaninfo);
		return rv;
	}
	
	/**
	 * Checks if the provided argument is a valid duration
	 * @param duration2
	 * @return
	 * 	true of duration2 == 30 minutes
	 */
	private boolean isValidDuration(long duration2) {
		return duration2==30*HospitalDate.ONE_MINUTE;
	}
	/**
	 * Checks if the given argument is a valid argument for scan info.
	 * @param scaninfo
	 * @return
	 * 	false if the provided scaninfo argument == null
	 */
	private boolean isValidScanInfo(String scaninfo) {
		return scaninfo!=null;
	}
}
