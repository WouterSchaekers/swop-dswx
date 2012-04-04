package machine;


public class MachinePoolBuilder
{
	
	public MachinePool create() {
		MachinePool rv = new MachinePool();
		rv.addBuilder(new BloodAnalyserBuilder());
		rv.addBuilder(new UltraSoundScannerBuilder());
		rv.addBuilder(new XRayScannerBuilder());
	    rv.addBuilder(new SurgicalEquipmentBuilder());
		
		return rv;
	}

}
