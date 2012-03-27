package machine;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.HospitalDate;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

/**
 * This class is merely a collection of all machines in existence.
 */
public class MachinePool
{
	private Collection<Machine> allMachines = new ArrayList<Machine>();;
	private Collection<MachineBuilder> bob = new ArrayList<MachineBuilder>();
	/**
	 * Adds a machine to the machine pool
	 * 
	 * @param m
	 * @throws InvalidSerialException 
	 * @throws InvalidLocationException 
	 */
	public void addMachine(MachineBuilder m) throws InvalidLocationException, InvalidSerialException {
		
		Machine machine = m.build();
		if(	alreadyContains(machine.getSerial()))
			throw new InvalidSerialException();
		allMachines.add(m.build());
	}

	/**
	 * Removes a machine from the machine pool
	 * 
	 * @param m
	 */
	public void removeMachine(Machine m) {
		allMachines.remove(m);
	}
	public void addBuilder(MachineBuilder builder)
	{
		bob.add(builder);
	}
	public Collection<MachineBuilder> getAllBuilders()
	{
		ArrayList<MachineBuilder> rv = new ArrayList<MachineBuilder>();
		for(MachineBuilder builder:this.bob)
			rv.add(builder.newBuilder());
		return rv;
	}
	/**
	 * Returns a copy of the collection of all the machines in our hospital.
	 * 
	 * @return
	 */
	public Collection<Machine> getAllMachines() {
		return new ArrayList<Machine>(this.allMachines);
	}


	public void updateTimeTables(HospitalDate newDate) {
		for (Machine machine : allMachines) {
			machine.updateTimeTable(newDate);
		}
	}

	private boolean alreadyContains(int serial)  {
		
		for (Machine m : allMachines)
			if (m.getSerial() == serial)
				return true;
		return false;
	}


}
