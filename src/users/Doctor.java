package users;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler2.ScheduledTask;
import system.Whereabouts;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class Doctor extends SchedulableUser implements DoctorIN
{
	public Doctor(String name, Whereabouts preference) throws InvalidNameException {
		super(name, preference);
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(HospitalDate startDate,
			HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate,
				duration);
	}
	
	public Whereabouts getLocationAt(HospitalDate hospitalDate){
		for(ScheduledTask scheduledTask : this._scheduledTasks){
			if(scheduledTask.getTimeSlot().contains(hospitalDate)){
				return scheduledTask.getLocation();
			}
		}
		throw new NullPointerException("This person has no location at that moment.");
	}
}