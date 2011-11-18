package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class XRayScanner extends Machine
{

	public XRayScanner(int serial,String location) throws InvalidLocationException, InvalidSerialException{
		super(serial, location);
	}

}
