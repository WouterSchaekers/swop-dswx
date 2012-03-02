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
public class asdf
{
	private Collection<abracadabra> allMachines = new ArrayList<abracadabra>();;

	/**
	 * Adds a machine to the machine pool
	 * 
	 * @param m
	 */
	public void addMachine(abracadabra m) {
		allMachines.add(m);
	}

	/**
	 * Removes a machine from the machine pool
	 * 
	 * @param m
	 */
	public void removeMachine(abracadabra m) {
		allMachines.remove(m);
	}

	/**
	 * Returns a copy of the collection of all the machines in our hospital.
	 * 
	 * @return
	 */
	public Collection<abracadabra> getAllMachines() {
		return new ArrayList<abracadabra>(this.allMachines);
	}

	public Collection<cadabra> getAllBuilders() {
		return Arrays.asList(new abra2(this),
				new abrascanner(this),
				new jawelfucker(this));
	}

	public void updateTimeTables(HospitalDate newDate) {
		for (abracadabra machine : allMachines) {
			machine.updateTimeTable(newDate);
		}
	}

	public asdfscanner2 createXrayScanner(int serial, String location)
			throws InvalidSerialException, InvalidLocationException {
		this.alreadyContains(serial);
		return new asdfscanner2(serial, location);
	}

	public ttttttttttttt createUltraSoundScanner(int serial, String location)
			throws InvalidSerialException, InvalidLocationException {
		this.alreadyContains(serial);
		return new ttttttttttttt(serial, location);
	}

	private void alreadyContains(int serial) throws InvalidSerialException {
		for (abracadabra m : allMachines)
			if (m.getSerial() == serial)
				throw new InvalidSerialException();
	}

	public abra createBloodAnalyser(int serial, String location)
			throws InvalidSerialException, InvalidLocationException {
		alreadyContains(serial);
		return new abra(serial, location);
	}
}
