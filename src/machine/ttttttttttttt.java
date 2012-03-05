package machine;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class ttttttttttttt extends abracadabra
{
	/**
	 * Creates an ultrasound scanner, exceptions are thrown as in the super
	 * class: {@link abracadabra#Machine(int, String)}
	 * 
	 * @throws InvalidTimeSlotException
	 * */
	ttttttttttttt(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}

	@Override
	public void scheduleAt(TimeSlot t) throws InvalidSchedulingRequestException {
		this.getTimeTable().addTimeSlot(t);
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) {
		return this.getTimeTable().hasFreeSlotAt(startDate, stopDate);
	}
}