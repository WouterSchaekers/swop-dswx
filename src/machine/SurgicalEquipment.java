package machine;

import system.Location;
import exceptions.InvalidLocationException;

public class SurgicalEquipment extends Machine
{
	/**
	 * Creates an SurgicalEquipment.
	 * 
	 * @param serial
	 *            The (unique) serial of this machine.
	 * @param location
	 *            The location of this machine inside the campus.
	 * @param campusLocation
	 *            The campus of this machine.
	 * @throws InvalidLocationException
	 *             If the location provided is null or an empty string.
	 */
	SurgicalEquipment(int serial, String location, Location campusLocation) {
		super(serial, location, campusLocation);
	}
}