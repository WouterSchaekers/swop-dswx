package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class BloodanalyzerBuilder extends MachineBuilder
{
	public String toString()
	{
		return "Blood analysis factory";
		
	}
	public Machine build(int serial,String location) throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException
	{
		return new BloodAnalyser(serial, location);
	}
}
