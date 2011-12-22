package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents something that can create UltraSoundScans.
 */
public class UltraSoundScanFactory extends MedicalTestFactory
{
	private String scaninfo;
	private int duration;
	private boolean recordVidSet=false;
	private boolean recordImageSet=false;
	private boolean recordVid;
	private boolean recordImages;
	UltraSoundScanFactory(){setDuration(30);}
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
		if(inValidScanInfo(scaninfo))
			throw new IllegalArgumentException("invalid scan info");
		this.scaninfo=scaninfo;
	}
	@Override
	public MedicalTest create() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException {
		if(!ready())
			throw new IllegalArgumentException();
		return new UltraSoundScan(scaninfo,recordVid,recordImages,duration);
	}
	
	@Override
	public String toString()
	{
		return "ultrasound scan";
	}
	private boolean ready() {
		boolean rv= true;
		rv	&=recordImageSet;
		rv	&=recordVidSet;
		rv  &=!inValidScanInfo(scaninfo);
		return rv;
	}
	/**
	 * Sets the duration of this scan
	 * @param duration
	 */
	private void setDuration(int duration) {
		if(inValidDuration(duration))
			throw new IllegalArgumentException();
		this.duration=duration;
		
	}
	private boolean inValidDuration(int i) {
		return i!=30;
	}
	private boolean inValidScanInfo(String scaninfo) {
		return scaninfo==null;
	}
}
