package machine;

import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class XRayScannerBuilder extends MachineBuilder
{
	XRayScannerBuilder(MachinePool pool) {
		super(pool);

	}

	public String toString() {
		return "XrayScanner";
	}

	public XRayScanner build(int serial, Location location)
			throws InvalidLocationException, InvalidSerialException {
		return pool.createXrayScanner(serial, location);

	}
}
