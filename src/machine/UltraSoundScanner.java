package machine;

import system.Location;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents an UltraSound Scanner.
 */
public class UltraSoundScanner extends Machine
{
	/**
	 * Creates an ultrasound scanner, exceptions are thrown as in the super
	 * class: {@link Machine#Machine(int, String)}
	 * 
	 * @throws InvalidTimeSlotException
	 * */
	UltraSoundScanner(int serial, String loc, Location location) {
		super(serial, loc, location);
	}
}