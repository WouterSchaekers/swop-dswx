package machine;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.HospitalDate;
import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

/**
 * This class is a collection of all machines in existence.
 */
public class MachinePool
{
	private Collection<Machine> allMachines_ = new ArrayList<Machine>();;
	private Collection<MachineBuilder> allBuilders_ = new ArrayList<MachineBuilder>();

	/**
	 * Adds a machine to the MachinePool and returns the new Machine.
	 * 
	 * @param builder
	 *            The Builder of the Machine.
	 * @return The created Machine.
	 * @throws InvalidSerialException
	 *             The given serial is invalid.
	 * @throws InvalidLocationException
	 *             One of the given locations in invalid.
	 */
	public Machine addMachine(MachineBuilder builder) throws InvalidSerialException, InvalidLocationException {
		Machine machine = builder.build();
		if (alreadyContains(machine.getSerial()))
			throw new InvalidSerialException();
		if (!isValidLocation(machine.getLocationWithinCampus()))
			throw new InvalidLocationException();
		if (!isValidCampusLocation(machine.getCampusLocation()))
			throw new InvalidLocationException();
		allMachines_.add(builder.build());
		return machine;
	}

	/**
	 * Adds a Builder to the pool of Builders.
	 * 
	 * @param builder
	 *            The Builder that has to be added.
	 */
	void addBuilder(MachineBuilder builder) {
		allBuilders_.add(builder);
	}

	/**
	 * Returns a deep copy of the list of all the Builders in the pool.
	 * 
	 * @return A deep copy of the list of all the Builders in the pool.
	 */
	public Collection<MachineBuilder> getAllBuilders() {
		ArrayList<MachineBuilder> rv = new ArrayList<MachineBuilder>();
		for (MachineBuilder builder : this.allBuilders_)
			rv.add(builder.newBuilder());
		return rv;
	}

	/**
	 * Returns a copy of the list of all the Machines in the pool.
	 * 
	 * @return A copy of the list of all the Machines in the pool.
	 */
	public Collection<Machine> getAllMachines() {
		return new ArrayList<Machine>(this.allMachines_);
	}

	/**
	 * Updates the timeTables of all the Machines.
	 * 
	 * @param newDate
	 *            The Date on which the timeTable has to be updated with.
	 */
	public void updateTimeTables(HospitalDate newDate) {
		for (Machine machine : allMachines_) {
			machine.updateTimeTable(newDate);
		}
	}

	/**
	 * Checks whether the pool already contains a Machine with the given serial.
	 * 
	 * @param serial
	 *            The serial that has to be checked.
	 * @return True if the pool already contains a Machine with the given
	 *         serial.
	 */
	private boolean alreadyContains(int serial) {
		for (Machine m : allMachines_)
			if (m.getSerial() == serial)
				return true;
		return false;
	}

	/**
	 * Checks whether the given location within a campus is valid.
	 * 
	 * @param location
	 *            The location within a campus that has to be checked.
	 * @return True if the given location is nog null and not emtpy.
	 */
	private boolean isValidLocation(String location) {
		return location != null && !location.isEmpty();
	}

	/**
	 * Checks whether the given location is valid.
	 * 
	 * @param location
	 *            The location that has to be checked.
	 * @return True if the given location is not null.
	 */
	private boolean isValidCampusLocation(Location location) {
		return location != null;
	}
}