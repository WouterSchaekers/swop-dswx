package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class XrayScannerBuilder extends MachineBuilder
{
	public String toString()
	{
		return "XrayScanner factory";
	}
	public XRayScanner build(int serial,String location) throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException
	{
		return new XRayScanner(serial, location);
		
	}
}
