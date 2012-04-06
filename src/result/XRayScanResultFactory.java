package result;

import exceptions.FactoryInstantiationException;

/**
 * Class representing a XRayScan Factory.
 */
@result.ResutlsAPI
public class XRayScanResultFactory implements ResultFactory
{

	private String abnormalities_;
	private Integer numberOfImages_;

	/**
	 * Creates and returns a Result.
	 * 
	 * @return The result.
	 * @throws FactoryInstantiationException
	 *             The factory was not fully instantiated yet.
	 */
	@Override
	public Result create() throws FactoryInstantiationException {
		if (!isReady())
			throw new FactoryInstantiationException("The XRayScanResultFactory is not ready yet!");
		return new XRayScanResult(this.abnormalities_, this.numberOfImages_);
	}

	/**
	 * @return True if the abnormalities and numberOfImages are ready.
	 */
	private boolean isReady() {
		if (!abnormalitiesReady())
			return false;
		if (!numberOfImagesReady())
			return false;
		return true;
	}

	/**
	 * @return True if the numberOfImages is not null and not negative.
	 */
	private boolean numberOfImagesReady() {
		return this.numberOfImages_ != null && this.numberOfImages_ >= 0;
	}

	/**
	 * @return True if the abnormalities is not null.
	 */
	private boolean abnormalitiesReady() {
		return this.abnormalities_ != null;
	}

	/**
	 * Sets the found abnormalities.
	 * 
	 * @param abnormalities
	 *            The found abnormalities.
	 */
	@result.ResutlsAPI
	public void setAbnormalities(String abnormalities) {
		this.abnormalities_ = abnormalities;
	}

	/**
	 * Sets the number of taken images.
	 * 
	 * @param numberOfImages
	 *            The number of taken images.
	 */
	@result.ResutlsAPI
	public void setNumberOfImages(int numberOfImages) {
		this.numberOfImages_ = numberOfImages;
	}
}