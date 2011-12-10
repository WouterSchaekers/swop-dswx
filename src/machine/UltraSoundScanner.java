package machine;

import scheduler.Date;
import scheduler.TimeSlot;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class UltraSoundScanner extends Machine
{
	/**
	 * Creates an ultrasound scanner, exceptions are thrown as in the super class: 
	 * {@link Machine#Machine(int, String)} 
	 * */
	public UltraSoundScanner(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}

	@Override
	public boolean canBeScheduledOn(Date startDate, Date stopDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void scheduleAt(TimeSlot t) {
		// TODO Auto-generated method stub
		
	}
}
