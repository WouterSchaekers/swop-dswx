package resources;

import java.util.Collection;

/**
 * This class is merely a collection of all machines in existence.
 */
public class MachinePool
{
	private Collection<Machine> allMachines;

	public void addMachine(Machine m) {
		allMachines.add(m);
	}

	public void removeMachine(Machine m) {
		allMachines.remove(m);
	}
	
	public Collection<Machine> getAllMachines() {
		return this.allMachines;
	}
}
