package machine;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class XRayScanner extends Machine
{
	/**
	 * Creates an XRayScanner scanner, exceptions are thrown as in the super
	 * class: {@link Machine#Machine(int, String)}
	 * 
	 * @throws InvalidTimeSlotException
	 * */
	XRayScanner(int serial, Location location) throws InvalidLocationException,
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

	@Override
	public boolean canTravel() {
		return false;
	}
}