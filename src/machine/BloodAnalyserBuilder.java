package machine;

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

	public Machine build(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		return pool.createBloodAnalyser(serial, location);
	}
}
