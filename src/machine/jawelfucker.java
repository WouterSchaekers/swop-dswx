package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class jawelfucker extends cadabra
{
	jawelfucker(asdf pool) {
		super(pool);

	}

	public String toString() {
		return "XrayScanner";
	}

	public asdfscanner2 build(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		return pool.createXrayScanner(serial, location);

	}
}
