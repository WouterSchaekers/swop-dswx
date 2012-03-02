package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class abrascanner extends cadabra
{
	abrascanner(asdf pool) {
		super(pool);
	}

	public String toString() {
		return "UltraSoundScanner";

	}

	public ttttttttttttt build(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		return new ttttttttttttt(serial, location);
	}
}
