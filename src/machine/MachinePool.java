package machine;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.HospitalDate;
import system.Location;
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
	public Machine addMachine(MachineBuilder m) throws InvalidSerialException, InvalidLocationException {
		Machine machine = m.build();
		if (alreadyContains(machine.getSerial()))
			throw new InvalidSerialException();
		if (!isValidLocation(machine.getLocationWithinCampus()))
			throw new InvalidLocationException();
		if (!isValidCampusLocation(machine.getCampusLocation()))
			throw new InvalidLocationException();
		allMachines.add(m.build());
		return machine;
	}

	void addBuilder(MachineBuilder builder) {
		bob.add(builder);
	}

	public Collection<MachineBuilder> getAllBuilders() {
		ArrayList<MachineBuilder> rv = new ArrayList<MachineBuilder>();
		for (MachineBuilder builder : this.bob)
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

	private boolean alreadyContains(int serial) {
		for (Machine m : allMachines)
			if (m.getSerial() == serial)
				return true;
		return false;
	}

	private boolean isValidLocation(String location) {
		return location != null && !location.isEmpty();
	}

	private boolean isValidCampusLocation(Location location) {
		return location != null;
	}
}