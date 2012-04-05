package medicaltest;

import exceptions.FactoryInstantiationException;

/**
 * A UltraSoundScanFactory is a factory, used to create an UltraSound Scan.
 */
public class UltraSoundScanFactory extends MedicalTestFactory
{
	private String focus_;
	private boolean recordVid_;
	private boolean recordVidSet_;
	private boolean recordImages_;
	private boolean recordImagesSet_;

	/**
	 * Default constructor.
	 */
	public UltraSoundScanFactory() {
		this.recordVidSet_ = false;
		this.recordImagesSet_ = false;
	}

	/**
	 * Method to set the fact whether there will be recorded video.
	 * 
	 * @param recordVid
	 *            True if there have to be video recorded.
	 */
	public void setRecordVid(boolean recordVid) {
		this.recordVid_ = recordVid;
	}

	/**
	 * Method to set the fact whether there will be recorded images.
	 * 
	 * @param recordImages
	 *            True if there have to be images recorded.
	 */
	public void setRecordImages(boolean recordImages) {
		this.recordImages_ = recordImages;
	}

	/**
	 * Sets the focus of the ultrasound scan.
	 * 
	 * @param focus
	 *            The focus of this ultrasound scan.
	 */
	public void setFocus(String focus) throws IllegalArgumentException {
		this.focus_ = focus;
	}

	/**
	 * Creates a UltraSound Scan built from the given information.
	 * 
	 * @return A UltraSound Scan built from the given information.
	 * @throws FactoryInstantiationException
	 *             The factory was not ready yet.
	 */
	@Override
	public MedicalTest create() throws FactoryInstantiationException {
		if (!ready())
			throw new FactoryInstantiationException("UltraSoundScanFactory is not properly instantiated yet.");
		return new UltraSoundScan(this.patientFile_, this.creationDate_, this.focus_, this.recordVid_,
				this.recordImages_);
	}

	/**
	 * The textual representation of the UltraSoundScan Factory.
	 */
	@Override
	public String toString() {
		return "UltraSound Scan Factory";
	}

	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the record images and the record video are set, and when
	 *         the focus is valid.
	 */
	private boolean ready() {
		return super.isReady() && this.recordImagesSet_ && this.recordVidSet_ && isValidFocus();
	}

	/**
	 * Checks whether the focus is valid.
	 * 
	 * @return True if the focus is not null and not empty.
	 */
	private boolean isValidFocus() {
		return this.focus_ != null && !this.focus_.isEmpty();
	}

	/**
	 * Returns a new instance of the current factory.
	 * 
	 * @return A new instance of the current factory.
	 */
	@Override
	public MedicalTestFactory newInstance() {
		return new UltraSoundScanFactory();
	}
}