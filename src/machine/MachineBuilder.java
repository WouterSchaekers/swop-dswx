package machine;

import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public abstract class MachineBuilder
{
	protected Integer serial_ = null;
	protected Location location_ = null;
	protected String loc_;

	abstract Machine build() throws InvalidLocationException, InvalidSerialException;

	/**
	 * Creates a new builder of the same type
	 * 
	 * @return
	 */
	abstract MachineBuilder newBuilder();

	public final void setSerial(int serial) throws IllegalArgumentException {
		if (this.serial_ == null)
			this.serial_ = serial;
		else
			throw new IllegalArgumentException("You can only set serial once");
	}

	public final void setLocation(Location location) throws IllegalArgumentException {
		if (this.location_ == null)
			this.location_ = location;
		else
			throw new IllegalArgumentException("You can only set location once");
	}

	public final void setLocationWithinCampus(String location) {
		if(location.isEmpty())
			throw new IllegalArgumentException("Invalid location");
		this.loc_ = location;
	}
	
	abstract boolean sameType(MachineBuilder builder);
}
