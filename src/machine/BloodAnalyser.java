package machine;

import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class BloodAnalyser extends Machine
{
	/**
	 * Creates an BloodAnalyser scanner, exceptions are thrown as in the super
	 * class: {@link Machine#Machine(int, String)}
	 * 
	 * @throws InvalidTimeSlotException
	 * */
	BloodAnalyser(int serial, String loc, Location location) throws InvalidLocationException,
			InvalidSerialException {
		super(serial, loc, location);
	}
}