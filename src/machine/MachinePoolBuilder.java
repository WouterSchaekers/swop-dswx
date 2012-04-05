package machine;

/**
 * This class instantiates a MachinePool with all the possible Builders.
 */
public class MachinePoolBuilder
{
	/**
	 * Returns a MachinePool with all the possible Builders.
	 * 
	 * @return A MachinePool with all the possible Builders.
	 */
	public MachinePool create() {
		MachinePool rv = new MachinePool();
		rv.addBuilder(new BloodAnalyserBuilder());
		rv.addBuilder(new UltraSoundScannerBuilder());
		rv.addBuilder(new XRayScannerBuilder());
		rv.addBuilder(new SurgicalEquipmentBuilder());
		return rv;
	}
}