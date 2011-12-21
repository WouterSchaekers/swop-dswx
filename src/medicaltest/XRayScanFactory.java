package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

/**
 * 
 * @author Dieter
 * 
 */
public class XRayScanFactory extends MedicalTestFactory
{
	private String bodypart;
	private int num;
	private float zoomlevel;

	/**
	 * 
	 * @param bodypart
	 */
	public void setBodyPart(String bodypart) {
		if (invalidBodyPart(bodypart))
			throw new IllegalArgumentException("bodypart argument was invalid");
		this.bodypart = bodypart;
	}

	/**
	 * 
	 * @param zoomlevel
	 */
	public void setZoomLevel(float zoomlevel) {
		if (isInValidZoomLevel(zoomlevel))
			throw new IllegalArgumentException("invalid zoomlevel");
		this.zoomlevel = zoomlevel;

	}

	/**
	 * 
	 * @param num
	 */
	public void numberOfNeededImages(int num) {
		if (inValidNumberOfImages(num))
			throw new IllegalArgumentException("invalid number of images");
		this.num = num;
	}

	@Override
	public MedicalTest create() throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException,
			FactoryInstantiation {
		if (this.ready())
			return new XRayScan(bodypart);
		throw new FactoryInstantiation();
	}

	private boolean ready() {
		boolean rv = true;
		rv &= !invalidBodyPart(bodypart);
		rv &= !isInValidZoomLevel(zoomlevel);
		rv &= !inValidNumberOfImages(num);
		return rv;
	}

	private boolean inValidNumberOfImages(int num) {
		return num > 0;
	}

	private boolean isInValidZoomLevel(float level) {
		return level < 1 || level > 3;
	}

	private boolean invalidBodyPart(String name) {
		return name != null;
	}

}
