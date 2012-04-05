package machine;

import system.Location;

/**
 * This class represents a XRay Scanner.
 */
public class XRayScanner extends Machine
{
	/**
	 * Creates a XRay Scanner.
	 * 
	 * @param serial
	 *            The (unique) serial of this machine.
	 * @param location
	 *            The location of this machine inside the campus.
	 * @param campusLocation
	 *            The campus of this machine.
	 */
	XRayScanner(int serial, String loc, Location location) {
		super(serial, loc, location);
	}
}