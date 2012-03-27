package machine;

import system.Location;
import exceptions.IllegalSetException;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public abstract class MachineBuilder
{
	protected Integer serial_ = null;
	protected Location location_ = null;
	abstract Machine build()
	throws InvalidLocationException, InvalidSerialException;
/**
 * Creates a new builder of the same type
 * @return
 */
	abstract MachineBuilder newBuilder();
	public final void setSerial(int serial) throws IllegalSetException
	{
		if(this.serial_==null)
			this.serial_=serial;
		else
			throw new IllegalSetException("You can only set serial once");
	}
	public final void setLocation(Location location) throws IllegalSetException
	{
		if(this.location_==null)
			this.location_ = location;
		else
			throw new IllegalSetException("You can only set location once");
	}
}
