package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class BloodAnalyserBuilder extends MachineBuilder
{

	BloodAnalyserBuilder( ) {
		super();
	}
	@Override
	public String toString() {
		return "Blood analyser";

	}
	@Override
	Machine build()
			throws InvalidLocationException, InvalidSerialException {
		return new BloodAnalyser(serial_, loc_, location_);
	}

	@Override
	MachineBuilder newBuilder() {
		return new BloodAnalyserBuilder();
	}
	@Override
	boolean sameType(MachineBuilder builder) {
		return builder instanceof BloodAnalyserBuilder;
	}
}
