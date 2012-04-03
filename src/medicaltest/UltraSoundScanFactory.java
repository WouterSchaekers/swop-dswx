package medicaltest;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

/**
 * This class represents something that can create UltraSoundScans.
 */
public class UltraSoundScanFactory extends MedicalTestFactory
{
	private String scaninfo;
	private boolean recordVidSet = false;
	private boolean recordImageSet = false;
	private boolean recordVid;
	private boolean recordImages;

	public UltraSoundScanFactory() {
	}

	/**
	 * Sets if there should be a video recoded
	 * 
	 * @param recordVid
	 */
	public void setRecordVid(boolean recordVid) {
		this.recordVidSet = true;
		this.recordVid = recordVid;
	}

	public void setRecordImages(boolean recordImages) {
		this.recordImageSet = true;
		this.recordImages = recordImages;
	}

	/**
	 * Sets the scan info
	 * 
	 * @param scaninfo
	 * @throws IllegalArgumentException
	 *             if null was given as an argument
	 */
	public void setScanInfo(String scaninfo) throws IllegalArgumentException {
		if (!isValidScanInfo(scaninfo))
			throw new IllegalArgumentException("invalid scan info");
		this.scaninfo = scaninfo;
	}

	@Override
	public MedicalTest create() throws FactoryInstantiationException {
		if (!ready())
			throw new FactoryInstantiationException("UltraSoundScanFactory is not properly instantiated yet.");
		UltraSoundScan ultraSoundScan = null;
		try {
			ultraSoundScan = new UltraSoundScan(patientFile_, creationDate_, scaninfo, recordVid, recordImages);
		} catch (InvalidAmountException e) {
			System.out.println(e);
		} catch (InvalidHospitalDateException e) {
			System.out.println(e);
		}
		return ultraSoundScan;
	}

	@Override
	public String toString() {
		return "ultrasound scan";
	}

	/**
	 * checks if the ultrasound scan object is ready to be created.
	 * 
	 * @return
	 */
	private boolean ready() {
		boolean rv = super.isReady();
		rv &= recordImageSet;
		rv &= recordVidSet;
		rv &= isValidScanInfo(scaninfo);
		return rv;
	}

	/**
	 * Checks if the given argument is a valid argument for scan info.
	 * 
	 * @param scaninfo
	 * @return false if the provided scaninfo argument == null
	 */
	private boolean isValidScanInfo(String scaninfo) {
		return scaninfo != null;
	}

	@Override
	public MedicalTestFactory newInstance() {
		return new UltraSoundScanFactory();
	}
}
