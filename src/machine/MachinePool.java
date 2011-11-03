package machine;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is merely a collection of all machines in existence.
 */
public class MachinePool
{
	private Collection<Machine> allMachines=new ArrayList<Machine>();

	public void addMachine(Machine m) {
		allMachines.add(m);
	}

	public void removeMachine(Machine m) {
		allMachines.remove(m);
	}
	
	public Collection<Machine> getAllMachines() {
		return this.allMachines;
	}

	public void addXrayScanner(int id, String location) {
		this.addMachine(new XRayScanner(id,location));
		
	}

	public void addBloodAnalyzer(int id, String location) {
		// TODO Auto-generated method stub
		
	}

	public void addUltraSoundScanner(int id, String location) {
		// TODO Auto-generated method stub
		
	}
}
