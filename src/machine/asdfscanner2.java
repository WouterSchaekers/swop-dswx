package machine;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class asdfscanner2 extends abracadabra
{
	/**
	 * Creates an XRayScanner scanner, exceptions are thrown as in the super
	 * class: {@link abracadabra#Machine(int, String)}
	 * 
	 * @throws InvalidTimeSlotException
	 * */
	asdfscanner2(int serial, String location) throws InvalidLocationException,
			InvalidSerialException {
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
