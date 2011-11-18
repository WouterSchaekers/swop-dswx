package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import task.Resource;

/**
 * This class represents a machine in the hospital.
 */
public class Machine implements Resource
{

	private final int serial;
	private String location = "";

	/**
	 * Default constructor.
	 * 
	 * @param serial
	 *            The serial of this machine.
	 */
	public Machine(int serial, String location) throws InvalidLocationException, InvalidSerialException{
		if(location == null || location == ""){
			throw new InvalidLocationException("Location is not set or is empty.");
		}
		if(serial == 0){
			throw new InvalidSerialException("Serial must be different from zero.");
		}
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

	@Override
	public boolean equals(Object o) {
		if (o instanceof Machine)
			return ((Machine) o).serial == this.serial;
		return false;

	}
}
