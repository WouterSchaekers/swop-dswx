package machine;

import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class BloodAnalyserBuilder extends MachineBuilder
{

	BloodAnalyserBuilder(MachinePool pool) {
		super(pool);
	}

	public String toString() {
		return "Blood analyzer";

	}

	public Machine build(int serial, Location location)
			throws InvalidLocationException, InvalidSerialException {
		return pool.createBloodAnalyser(serial, location);
	}
}
