package resources;

/**
 * This class represents a machine in the hospital.
 */
public class Machine
{
	
	private int serial = -1;
	private String location = "";
	
	/**
	 * Default constructor.
	 * @param serial
	 * The serial of this machine.
	 */
	public Machine(int serial, String location) {
		this.serial = serial;
		this.location = location;
	}
	
	/**
	 * @return The serial of this machine.
	 */
	public int getSerial() {
		return this.serial;
	}

	/**
	 * @return The location of this machine.
	 */
	public String getLocation() {
		return this.location;
	}
}
