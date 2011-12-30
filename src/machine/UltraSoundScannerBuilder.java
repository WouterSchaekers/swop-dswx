package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScannerBuilder extends MachineBuilder
{
	UltraSoundScannerBuilder(MachinePool pool) {
		super(pool);
	}
	public String toString()
	{
		return "UltraSoundScanner factory";
		
	}
	public UltraSoundScanner build(int serial,String location) throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException
	{
		return new UltraSoundScanner(serial, location);
	}
}
