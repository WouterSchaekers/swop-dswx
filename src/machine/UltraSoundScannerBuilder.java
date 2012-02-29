package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class UltraSoundScannerBuilder extends MachineBuilder
{
	UltraSoundScannerBuilder(MachinePool pool) {
		super(pool);
	}

	public String toString() {
		return "UltraSoundScanner";

	}

	public UltraSoundScanner build(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		return new UltraSoundScanner(serial, location);
	}
}
