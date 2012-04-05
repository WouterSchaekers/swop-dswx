package machine;

import system.Location;

/**
 * This class represents a Surgical Equipment.
 */
public class SurgicalEquipment extends Machine
{
	/**
	 * Creates a Surgical Equipment.
	 * 
	 * @param serial
	 *            The (unique) serial of this machine.
	 * @param location
	 *            The location of this machine inside the campus.
	 * @param campusLocation
	 *            The campus of this machine.
	 */
	SurgicalEquipment(int serial, String location, Location campusLocation) {
		super(serial, location, campusLocation);
	}
}