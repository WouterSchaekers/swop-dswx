package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class BloodanalyserBuilder extends MachineBuilder
{
	
	BloodanalyserBuilder(MachinePool pool) {
		super(pool);
	}
	public String toString()
	{
		return "Blood analysis factory";
		
	}
	public Machine build(int serial,String location) throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException
	{
		return pool.createBloodAnalyser(serial,location);
	}
}
