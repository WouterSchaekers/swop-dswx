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
	private Collection<Machine> allMachines;

	public MachinePool(){
		allMachines = new ArrayList<Machine>();
	}
	
	public void addMachine(Machine m) {
		allMachines.add(m);
	}

	public void removeMachine(Machine m) {
		allMachines.remove(m);
	}
	
	public Collection<Machine> getAllMachines() {
		return this.allMachines;
	}

	public void addXrayScanner(int id, String location) throws InvalidLocationException, InvalidSerialException{
		this.addMachine(new XRayScanner(id,location));
		
	}

	public void addBloodAnalyzer(int id, String location) throws InvalidLocationException, InvalidSerialException{
		this.addMachine(new BloodAnalyser(id, location));
		
	}

	public void addUltraSoundScanner(int id, String location) throws InvalidLocationException, InvalidSerialException{
		this.addMachine(new UltraSoundScanner(id, location));
	}
}
