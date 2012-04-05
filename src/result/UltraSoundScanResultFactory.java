package result;

import exceptions.FactoryInstantiationException;
import result.UltrasoundScanResult.ScanedMassInfo;

public class UltraSoundScanResultFactory implements ResultFactory
{

	private String scanInfo_;
	private ScanedMassInfo scanedMassNature_;

	@Override
	public Result create() throws FactoryInstantiationException {
		if(! isready())
			throw new FactoryInstantiationException("ultrasoundscan result factory not sufficiently instantiated.");
		return new UltrasoundScanResult(scanInfo_,scanedMassNature_);
	}

	private boolean isready() {
		return scanInfo_!=null&&scanedMassNature_!=null;
	}
	public void setScannInfo(String scanInfo)
	{
		this.scanInfo_=scanInfo;
	}
	public void setScannedMassNature(ScanedMassInfo info)
	{
		this.scanedMassNature_=info;
	}
}
