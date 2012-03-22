package medicaltest;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * The Factory that creates an XRayScan This factory requires : A bodypart, a
 * zoomlevel and the number of needed images.
 */
public class XRayScanFactory extends MedicalTestFactory
{
	private String bodypart;
	private int num;
	private float zoomlevel;

	XRayScanFactory() {
	}

	/**
	 * Sets the bodypart the scan is used for ( in the form of a string), this
	 * argument can not be null.
	 * 
	 * @param bodypart
	 * @throws IllegalArgumentException
	 *             if the argument was null
	 */
	public void setBodyPart(String bodypart) {
		if (!isValidBodyPart(bodypart))
			throw new IllegalArgumentException("bodypart argument was invalid");
		this.bodypart = bodypart;
	}

	/**
	 * Sets the zoomlevel used for Xray scan.
	 * 
	 * @param zoomlevel
	 * @throws IllegalArgumentException
	 *             if the zoomlevel is invalid : below 1 or above 3
	 */
	public void setZoomLevel(float zoomlevel) {
		if (!isValidZoomLevel(zoomlevel))
			throw new IllegalArgumentException("invalid zoomlevel");
		this.zoomlevel = zoomlevel;

	}

	/**
	 * Sets the number of images that is required for this Xray scan
	 * 
	 * @param num
	 * @throws IllegalArgumentException
	 *             if the number of images is <= 0
	 */
	public void numberOfNeededImages(int num) {
		if (!isValidNumberOfImages(num))
			throw new IllegalArgumentException("invalid number of images");
		this.num = num;
	}

	/**
	 * Checks if all the arguments are sufficiently instantiated
	 * 
	 * @return true if bodypart,num and zoomlevel are valid
	 */
	private boolean ready() {
		boolean rv = true;
		rv &= isValidBodyPart(bodypart);
		rv &= isValidZoomLevel(zoomlevel);
		rv &= isValidNumberOfImages(num);
		return rv;
	}

	/**
	 * Checks if the number images required is invalid
	 * 
	 * @param num
	 * @return true if num < 0
	 */
	private boolean isValidNumberOfImages(int num) {
		return num < 0;
	}

	/**
	 * Checks if the zoomlevel is Valid.
	 * 
	 * @param level
	 * @return level<1||level>3
	 */
	private boolean isValidZoomLevel(float level) {
		return !(level < 1 || level > 3);
	}

	/**
	 * bodypart validity check:
	 * 
	 * @param name
	 * @return true if the argument is not null
	 */
	private boolean isValidBodyPart(String name) {
		return name != null;
	}

	@Override
	public MedicalTest create() throws FactoryInstantiationException{
		if (!this.ready())
			throw new FactoryInstantiationException("XRayScanFactory is not properly instantiated yet.");
		XRayScan xRayScan = null;
		try {
			xRayScan = new XRayScan(this.patientFile_, this.creationDate_, bodypart, num, zoomlevel);
		} catch (InvalidAmountException e) {
			System.out.println(e);
		} catch (InvalidHospitalDateException e) {
			System.out.println(e);
		}
		return xRayScan;
	}

}
