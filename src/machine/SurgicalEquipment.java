package machine;

import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class SurgicalEquipment extends Machine
{

	SurgicalEquipment(int serial, String loc, Location location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, loc, location);
	}
}
