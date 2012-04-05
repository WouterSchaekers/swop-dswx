package result;

public class UltrasoundScanResult implements Result
{
	public final ScanedMassInfo scannedMassInfo_;
	public final String scanInfo_;
	public UltrasoundScanResult(String scanInfo, ScanedMassInfo scanedMassNature) {
		this.scanInfo_=scanInfo;
		this.scannedMassInfo_=scanedMassNature;
	}

	public enum ScanedMassInfo
	{
		UNKOWN,BENIGN,MALIGNANT;
	}
}
