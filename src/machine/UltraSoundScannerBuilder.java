package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class UltraSoundScannerBuilder extends MachineBuilder
{
	UltraSoundScannerBuilder() {
		super();
	}

	public String toString() {
		return "UltraSoundScanner";

	}

	public UltraSoundScanner build()
			throws InvalidLocationException, InvalidSerialException {
		return new UltraSoundScanner(serial_, location_);
	}

	@Override
	MachineBuilder newBuilder() {
		return new UltraSoundScannerBuilder();
	}
}
