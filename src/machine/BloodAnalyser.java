package machine;

import system.Location;
import exceptions.InvalidLocationException;

public class BloodAnalyser extends Machine
{
	/**
	 * Creates an BloodAnalyser.
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
	BloodAnalyser(int serial, String location, Location campusLocation) {
		super(serial, location, campusLocation);
	}
}