package result;

/**
 * Class representing an UltraSoundScan Result.
 */
public class UltraSoundScanResult implements Result
{
	/**
	 * Information about the UltraSoundScan.
	 */
	public final String scanInfo_;
	/**
	 * The nature of the scanned mass.
	 */
	public final ScannedMassNature scannedMassNature_;

	/**
	 * Default constructor. Package visible, since it should only be used by the
	 * factory.
	 * 
	 * @param scanInfo
	 *            Information about the UltraSoundScan.
	 * @param scannedMassNature
	 *            The nature of the scanned mass.
	 */
	UltraSoundScanResult(String scanInfo, ScannedMassNature scannedMassNature) {
		this.scanInfo_ = scanInfo;
		this.scannedMassNature_ = scannedMassNature;
	}

	/**
	 * Enum representing the possible states of the scanned mass.
	 */
	public enum ScannedMassNature
	{
		UNKOWN, BENIGN, MALIGNANT;
	}
}