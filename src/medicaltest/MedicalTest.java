package medicaltest;

import java.util.Date;
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
	// all childclasses will have their names be final and static and will use
	// this var to store that information in.

	private final String TESTNAME;
	private final long DURATION;
	private TimeTable myTimeTable = new TimeTable();

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of this medical test.
	 * @param duration
	 *            The duration of this medical test.
	 * @throws InvalidNameException
	 *             If(!isValidName(name))
	 * @throws InvalidDurationException
	 *             If(!isValidDuration(duration))
	 */
	public MedicalTest(String name, long duration) throws InvalidNameException,
			InvalidDurationException {
		
		if (!isValidName(name)) 
			throw new InvalidNameException("Invalid name in constructor of MedicalTest!");
		if (!isValidDuration(duration))
			throw new InvalidDurationException("The duration of a MedicalTest must be strictly positive!");
	
		this.TESTNAME = name;
		this.DURATION = duration;
	}

	@Basic
	public String getTestName() {
		return this.TESTNAME;
	}

	@Basic
	public long getDURATION() {
		return DURATION;
	}
	
	/**
	 * @return True if n is a valid name for this medical test.
	 */ 
	private boolean isValidName(String n) {
		return n != null && n != "";
	}
	
	/**
	 * @return True if d is a valid duration for this medical test.
	 */
	private boolean isValidDuration(long d) {
		return d > 0;
	}
	
	public boolean canBeScheduledOn(Date startDate, Date stopDate)throws ImpossibleToScheduleException {
		return this.myTimeTable.hasFreeSlotAt(startDate,stopDate);
	}

	public TimeTable getTimeTable() {
		
		return new TimeTable(this.myTimeTable.getTimeSlots());
	}
	
	public void scheduleAt(TimeSlot timeSlot) throws ImpossibleToScheduleException {
		if(!isValidTimeSlot(timeSlot) {
			throw new InvalidTimeSlotException("Trying to schedule an invalid TimeSlot!");
		}
		this.myTimeTable.addTimeSlot(timeSlot)
	}
	
	private boolean isValidTimeSlot(TimeSlot t){
		//TODO: Implement
		return false;
	}

}
