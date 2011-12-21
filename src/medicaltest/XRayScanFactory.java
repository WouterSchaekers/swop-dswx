package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * The Factory that creates an XRayScan
 * This factory requires :
 * A bodypart, a zoomlevel and the number of needed images.
 */
public class XRayScanFactory extends MedicalTestFactory
{
	private String bodypart;
	private int num;
	private float zoomlevel;
	private int duration;
	XRayScanFactory(){setDuration(15);}
	/**
	 * Sets the bodypart the scan is used for ( in the form of a string), this argument can not be null.
	 * @param bodypart
	 * @throws IllegalArgumentException 
	 * 	if the argument was null
	 */
	public void setBodyPart(String bodypart) {
		if (invalidBodyPart(bodypart))
			throw new IllegalArgumentException("bodypart argument was invalid");
		this.bodypart = bodypart;
	}

	/**
	 * Sets the zoomlevel used for Xray scan.
	 * @param zoomlevel
	 * @throws IllegalArgumentException 
	 * 	if the zoomlevel is invalid : below 1 or above 3
	 */
	public void setZoomLevel(float zoomlevel) {
		if (isInValidZoomLevel(zoomlevel))
			throw new IllegalArgumentException("invalid zoomlevel");
		this.zoomlevel = zoomlevel;

	}

	/**
	 * Sets the number of images that is required for this Xray scan
	 * @param num
	 * @throws IllegalArgumentException
	 * 	if the number of images is <= 0
	 */
	public void numberOfNeededImages(int num) {
		if (inValidNumberOfImages(num))
			throw new IllegalArgumentException("invalid number of images");
		this.num = num;
	}
	/**
	 * Sets the duration for this medical test ( in minutes)
	 * @param duration
	 * @throws IllegalArgumentException 
	 * 	if the duration is < 15 min.
	 */
	private void setDuration(int duration)
	{
		if(inValidDuration(duration))
			throw new IllegalArgumentException("Illegal duration");
		this.duration=duration;
	}
	/**
	 * Checks if all the arguments are sufficiently instantiated
	 * @return 
	 * 	true if bodypart,num and zoomlevel are valid
	 */
	private boolean ready() {
		boolean rv = true;
		rv &= !invalidBodyPart(bodypart);
		rv &= !isInValidZoomLevel(zoomlevel);
		rv &= !inValidNumberOfImages(num);
		rv &= !inValidDuration(duration);
		return rv;
	}
	/**
	 * Checks if the number images required is invalid
	 * @param num
	 * @return true if num < 0
	 */
	private boolean inValidNumberOfImages(int num) {
		return num < 0;
	}
	/**
	 * Checks if the zoomlevel is Invalid.
	 * @param level
	 * @return level<1||level>3
	 */
	private boolean isInValidZoomLevel(float level) {
		return level < 1 || level > 3;
	}
	/**
	 * bodypart validity check:
	 * @param name
	 * @return true if the argument is not null
	 */
	private boolean invalidBodyPart(String name) {
		return name != null;
	}
	/**
	 * 
	 * @param duration2
	 * @return
	 */
	private boolean inValidDuration(int duration2) {
		return duration2<15;
	}
	@Override
	public MedicalTest create() throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException,
			FactoryInstantiation {
		if (this.ready())
			return new XRayScan(bodypart,num,zoomlevel,duration);
		throw new FactoryInstantiation();
	}

}
