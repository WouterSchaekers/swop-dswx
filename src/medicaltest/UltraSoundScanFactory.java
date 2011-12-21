package medicaltest;

/**
 * Class that creates ultrasoundScans
 */
public class UltraSoundScanFactory extends MedicalTestFactory
{
	private String scaninfo;
	private int duration;
	/**
	 * 
	 * @param scaninfo
	 */
	public void setScanInfo(String scaninfo)
	{
		if(inValidScanInfo(scaninfo))
			throw new IllegalArgumentException();
		this.scaninfo=scaninfo;
	}
	UltraSoundScanFactory(){setDuration(30);}
	/**
	 * 
	 * @param duration
	 */
	private void setDuration(int duration) {
		if(inValidDuration(duration))
			throw new IllegalArgumentException();
		this.duration=duration;
		
	}
	private boolean inValidDuration(int i) {
		return i!=30;
	}
	@Override
	public MedicalTest create() {
		// TODO Auto-generated method stub
		return new UltraSoundScan(scaninfo,duration);
	}
	
	@Override
	public String toString()
	{
		return "ultrasound scan";
	}
	private boolean inValidScanInfo(String scaninfo) {
		// TODO Auto-generated method stub
		return false;
	}
}
