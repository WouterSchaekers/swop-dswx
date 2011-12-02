package machine;

import java.util.ArrayList;
import java.util.Collection;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

/**
 * This class is merely a collection of all machines in existence.
 */
public class MachinePool
{
	private Collection<Machine> allMachines = new ArrayList<Machine>();;

	/**
	 * Adds a machine to  the machine pool
	 * @param m
	 */
	public void addMachine(Machine m) {
		allMachines.add(m);
	}

	/**
	 * Removes a machine from the machine pool
	 * @param m
	 */
	public void removeMachine(Machine m) {
		allMachines.remove(m);
	}

	/**
	 * Returns a copy of the collection of all the machines in our hospital.
	 * @return
	 */
	public Collection<Machine> getAllMachines() {
		return new ArrayList<Machine>(this.allMachines);
	}

 
}
