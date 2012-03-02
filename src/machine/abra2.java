package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class abra2 extends cadabra
{

	abra2(asdf pool) {
		super(pool);
	}

	public String toString() {
		return "Blood analyzer";

	}

	public abracadabra build(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		return pool.createBloodAnalyser(serial, location);
	}
}
