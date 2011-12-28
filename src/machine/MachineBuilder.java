package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public abstract class MachineBuilder
{
	public abstract Machine build(int serial,String location) throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException;
}
