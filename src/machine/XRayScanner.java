package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class XRayScanner extends Machine
{
	/**
	 * Creates an XRayScanner scanner, exceptions are thrown as in the super class: 
	 * {@link Machine#Machine(int, String)} 
	 * */
	public XRayScanner(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}

}
