package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class XrayScannerBuilder extends MachineBuilder
{
	XrayScannerBuilder(MachinePool pool) {
		super(pool);

	}

	public String toString() {
		return "XrayScanner";
	}

	public XRayScanner build(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		return pool.createXrayScanner(serial, location);

	}
}
