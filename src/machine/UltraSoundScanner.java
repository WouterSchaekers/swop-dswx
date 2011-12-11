package machine;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScanner extends Machine
{
	/**
	 * Creates an ultrasound scanner, exceptions are thrown as in the super class: 
	 * {@link Machine#Machine(int, String)} 
	 * @throws InvalidTimeSlotException 
	 * */
	public UltraSoundScanner(int serial, String location)
			throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException {
		super(serial, location);
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void scheduleAt(TimeSlot t) {
		// TODO Auto-generated method stub
		
	}
}
