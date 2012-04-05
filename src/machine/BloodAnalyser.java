package machine;

import system.Location;

/**
 * This class represents a BloodAnalyser.
 */
public class BloodAnalyser extends Machine
{
	/**
	 * Creates a BloodAnalyser.
	 * 
	 * @param serial
	 *            The (unique) serial of this machine.
	 * @param location
	 *            The location of this machine inside the campus.
	 * @param campusLocation
	 *            The campus of this machine.
	 */
	BloodAnalyser(int serial, String location, Location campusLocation) {
		super(serial, location, campusLocation);
	}
}