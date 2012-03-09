package machine;

import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * Adds a machine to the machine pool
	 * 
	 * @param m
	 */
	public void addMachine(Machine m) {
		allMachines.add(m);
	}

	/**
	 * Removes a machine from the machine pool
	 * 
	 * @param m
	 */
	public void removeMachine(Machine m) {
		allMachines.remove(m);
	}

	/**
	 * Returns a copy of the collection of all the machines in our hospital.
	 * 
	 * @return
	 */
	public Collection<Machine> getAllMachines() {
		return new ArrayList<Machine>(this.allMachines);
	}

	public Collection<MachineBuilder> getAllBuilders() {
		return Arrays.asList(new BloodAnalyserBuilder(this),
				new UltraSoundScannerBuilder(this),
				new XRayScannerBuilder(this));
	}

	public void updateTimeTables(HospitalDate newDate) {
		for (Machine machine : allMachines) {
			machine.updateTimeTable(newDate);
		}
	}

	public XRayScanner createXrayScanner(int serial, String location)
			throws InvalidSerialException, InvalidLocationException {
		this.alreadyContains(serial);
		return new XRayScanner(serial, location);
	}

	public UltraSoundScanner createUltraSoundScanner(int serial, String location)
			throws InvalidSerialException, InvalidLocationException {
		this.alreadyContains(serial);
		return new UltraSoundScanner(serial, location);
	}
	
	public BloodAnalyser createBloodAnalyser(int serial, String location)
			throws InvalidSerialException, InvalidLocationException {
		alreadyContains(serial);
		return new BloodAnalyser(serial, location);
	}

	private void alreadyContains(int serial) throws InvalidSerialException {
		for (Machine m : allMachines)
			if (m.getSerial() == serial)
				throw new InvalidSerialException();
	}


}
