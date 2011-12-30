package machine;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScanner extends Machine
{
	/**
	 * Creates an ultrasound scanner, exceptions are thrown as in the super class: 
	 * {@link Machine#Machine(int, String)} 
	 * @throws InvalidTimeSlotException 
	 * */
	UltraSoundScanner(int serial, String location)
			throws InvalidLocationException, InvalidSerialException, InvalidTimeSlotException {
		super(serial, location);
	}

	@Override
	public void scheduleAt(TimeSlot t) throws InvalidSchedulingRequestException {
		this.getTimeTable().addTimeSlot(t);
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate) {
		return this.getTimeTable().hasFreeSlotAt(startDate, stopDate);
	}
}
