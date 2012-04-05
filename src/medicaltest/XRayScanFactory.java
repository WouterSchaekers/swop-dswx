package medicaltest;

import exceptions.FactoryInstantiationException;

/**
 * A XRayScanFactory is a factory, used to create XRay Scans.
 */
public class XRayScanFactory extends MedicalTestFactory
{
	private String bodyPart_;
	private int numberOfImages_;
	private float zoomlevel_;

	/**
	 * Default constructor.
	 */
	public XRayScanFactory() {
		;
	}

	/**
	 * Sets the bodypart the scan is used for.
	 * 
	 * @param bodypart
	 *            The bodypart that will be examined during the XRay Scan.
	 */
	public void setBodyPart(String bodypart) {
		this.bodyPart_ = bodypart;
	}

	/**
	 * Sets the zoomlevel used for XRay Scan.
	 * 
	 * @param zoomlevel
	 *            The zoomlevel for the XRay Scan.
	 */
	public void setZoomLevel(float zoomlevel) {
		this.zoomlevel_ = zoomlevel;

	}

	/**
	 * Sets the number of images that is required for this XRay Scan
	 * 
	 * @param numberOfImages
	 *            The number of images that is required for this XRay Scan.
	 */
	public void setNumberOfNeededImages(int numberOfImages) {
		this.numberOfImages_ = numberOfImages;
	}

	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the body part, the zoom level and the number of images
	 *         are valid.
	 */
	private boolean ready() {
		return super.isReady() && isValidBodyPart() && isValidZoomLevel() && isValidNumberOfImages();
	}

	/**
	 * Checks whether the number images is valid.
	 * 
	 * @return True if the number of images is strickt positive.
	 */
	private boolean isValidNumberOfImages() {
		return this.numberOfImages_ > 0;
	}

	/**
	 * Checks Whether the zoomlevel is valid.
	 * 
	 * @return True if the zoomlevel is between 1 and 3.
	 */
	private boolean isValidZoomLevel() {
		return this.zoomlevel_ > 0 && this.zoomlevel_ < 4;
	}

	/**
	 * Checks Whether the bodypart is valid.
	 * 
	 * @return True if the bodypart is not null and not empty.
	 */
	private boolean isValidBodyPart() {
		return this.bodyPart_ != null && !this.bodyPart_.isEmpty();
	}

	/**
	 * Creates a XRay Scan built from the given information.
	 * 
	 * @return A XRay Scan built from the given information.
	 * @throws FactoryInstantiationException
	 *             The factory was not ready yet.
	 */
	@Override
	public MedicalTest create() throws FactoryInstantiationException {
		if (!this.ready())
			throw new FactoryInstantiationException("XRayScanFactory is not properly instantiated yet.");
		return new XRayScan(this.patientFile_, this.creationDate_, this.bodyPart_, this.numberOfImages_,
				this.zoomlevel_);
	}

	/**
	 * Returns a new instance of the current factory.
	 * 
	 * @return A new instance of the current factory.
	 */
	@Override
	public MedicalTestFactory newInstance() {
		return new XRayScanFactory();
	}
}