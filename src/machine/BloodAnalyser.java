package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class BloodAnalyser extends Machine
{

	public BloodAnalyser(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}

}
