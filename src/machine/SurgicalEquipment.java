package machine;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;

public class SurgicalEquipment extends Machine
{

	public SurgicalEquipment(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException {
		this.getTimeTable().addTimeSlot(timeSlot);
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) {
		return this.getTimeTable().hasFreeSlotAt(startDate, stopDate);
	}

}
