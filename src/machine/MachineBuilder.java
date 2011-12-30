package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public abstract class MachineBuilder
{
	protected MachinePool pool;
	MachineBuilder(MachinePool pool)
	{
		this.pool=pool;
	}
	public abstract Machine build(int serial,String location) throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException;
}
