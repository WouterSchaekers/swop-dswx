package machine;

import java.util.Date;
import scheduler.TimeSlot;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class BloodAnalyser extends Machine
{
	/**
	 * Creates an BloodAnalyser scanner, exceptions are thrown as in the super class: 
	 * {@link Machine#Machine(int, String)} 
	 * */
	public BloodAnalyser(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}

	@Override
	public boolean canBeScheduledOn(Date start, Date stop) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void scheduleAt(TimeSlot t) {
		// TODO Auto-generated method stub
		
	}
}
