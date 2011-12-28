package medicaltest;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;
import exceptions.*;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest implements Schedulable
{
	private final long DURATION;
	private TimeTable myTimeTable;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of this medical test.
	 * @param duration
	 *            The duration of this medical test.
	 * @throws InvalidDurationException
	 *             If(!isValidDuration(duration))
	 * @throws InvalidTimeSlotException 
	 */
	public MedicalTest(long duration) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		
		if (!isValidDuration(duration))
			throw new InvalidDurationException("The duration of a MedicalTest must be strictly positive!");

		this.DURATION = duration;
		this.myTimeTable = new TimeTable();
	}



	@Basic
	public long getDURATION() {
		return DURATION;
	}
	

	
	/**
	 * @return True if d is a valid duration for this medical test.
	 */
	private boolean isValidDuration(long d) {
		return d > 0;
	}
	
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate)throws InvalidSchedulingRequestException {
		return this.myTimeTable.hasFreeSlotAt(startDate,stopDate);
	}

	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return new TimeTable(this.myTimeTable.getTimeSlots());
	}
	
	public void scheduleAt(TimeSlot timeSlot) throws InvalidSchedulingRequestException{
		if(!isValidTimeSlot(timeSlot)) 
			throw new InvalidSchedulingRequestException("Trying to schedule an invalid TimeSlot!");
		
		this.myTimeTable.addTimeSlot(timeSlot);
	}
	
	private boolean isValidTimeSlot(TimeSlot t) throws InvalidSchedulingRequestException{
		return t != null && this.canBeScheduledOn(t.getStartPoint().getDate(), t.getStopPoint().getDate());
	}
	
	@Override
	public TimeSlot getFirstFreeSlotBetween(HospitalDate startDate,
			HospitalDate stopDate, long duration) throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

}
