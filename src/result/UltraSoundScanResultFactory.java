package result;

import exceptions.FactoryInstantiationException;
import result.UltraSoundScanResult.ScannedMassNature;

/**
 * Class representing an UltraSoundScan Factory.
 */
public class UltraSoundScanResultFactory implements ResultFactory
{

	private String scanInfo_;
	private ScannedMassNature scannedMassNature_;

	/**
	 * Creates and returns a Result.
	 * 
	 * @return The result.
	 * @throws FactoryInstantiationException
	 *             The factory was not fully instantiated yet.
	 */
	@Override
	public Result create() throws FactoryInstantiationException {
		if (!isready())
			throw new FactoryInstantiationException("The UltraSoundScanResultFactory is not ready yet!");
		return new UltraSoundScanResult(this.scanInfo_, this.scannedMassNature_);
	}

	/**
	 * @return True if the scanInfo and the scannedMassNature are valid.
	 */
	private boolean isready() {
		return scannedMassNatureReady() && scanInfoReady();
	}

	/**
	 * Sets the information about the UltraSoundScan.
	 * 
	 * @param scanInfo
	 *            The information about the UltraSoundScan.
	 */
	public void setScanInfo(String scanInfo) {
		this.scanInfo_ = scanInfo;
	}

	/**
	 * Sets the nature of the scanned mass.
	 * 
	 * @param scannedMassNature
	 *            The nature of the scanned mass.
	 */
	public void setScannedMassNature(ScannedMassNature scannedMassNature) {
		this.scannedMassNature_ = scannedMassNature;
	}

	/**
	 * @return True if the scanInfo is not null and not empty.
	 */
	private boolean scanInfoReady() {
		return this.scanInfo_ != null && !this.scanInfo_.isEmpty();
	}

	/**
	 * @return True if the scannedMassNature is not null.
	 */
	private boolean scannedMassNatureReady() {
		return this.scannedMassNature_ != null;
	}
}