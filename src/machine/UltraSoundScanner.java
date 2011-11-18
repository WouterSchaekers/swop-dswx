package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class UltraSoundScanner extends Machine
{

	public UltraSoundScanner(int serial, String location) throws InvalidLocationException, InvalidSerialException{
		super( serial, location);
	}

}
