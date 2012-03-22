package machine;

import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public abstract class MachineBuilder
{
	protected MachinePool pool;

	MachineBuilder(MachinePool pool) {
		this.pool = pool;
	}

	public abstract Machine build(int serial, Location location)
			throws InvalidLocationException, InvalidSerialException;
}
