package machine;

import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class XRayScanner extends Machine
{
	/**
	 * Creates an XRayScanner scanner, exceptions are thrown as in the super
	 * class: {@link Machine#Machine(int, String)}
	 * 
	 * @throws InvalidTimeSlotException
	 * */
	XRayScanner(int serial, Location location) throws InvalidLocationException,
			InvalidSerialException {
		super(serial, location);
	}
	

}