package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class XRayScannerBuilder extends MachineBuilder
{
	XRayScannerBuilder() {
		super();

	}

	public String toString() {
		return "XrayScanner";
	}

	XRayScanner build()
			throws InvalidLocationException, InvalidSerialException {
		return new XRayScanner(serial_, location_);
	}

	@Override
	MachineBuilder newBuilder() {
		return new XRayScannerBuilder();
	}

	@Override
	boolean sameType(MachineBuilder builder) {
		return builder instanceof XRayScannerBuilder;
	}
}
