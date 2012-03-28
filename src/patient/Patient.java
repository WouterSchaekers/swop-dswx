package patient;

import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import system.Location;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a patient. Patients are linked to any number of patient
 * files depending on which hospital they go to. Patients are Schedulables which
 * means they have TimeTables.
 */
class Patient implements Schedulable
{
	private String name;
	private TimeTable timeTable_;
	private Location location_;
	
	Patient(String name, Location location) throws InvalidNameException {
		if(!isValidName(name))
			throw new InvalidNameException("Invalid patient name given to Patient constructor!");
		this.name = name;
		location_ = location;
		timeTable_ = new TimeTable();
	}

	@Basic
	public String getName() {
		return this.name;
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		return timeTable_.hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return this.timeTable_;
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException {
		this.timeTable_.addTimeSlot(timeSlot);
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location,
			HospitalDate startDate, HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidHospitalDateArgument {
		return timeTable_.getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	@Override
	public void updateTimeTable(HospitalDate newDate) {
		timeTable_.updateTimeTable(newDate);
	}

	@Override
	public Location getLocation() {
		//TODO: moet Patient hier iets zinnigs kunnen returnen?
		// Hoe weet de patient zijn location dan? Moeten we die setten elke keer de tijd aanpast?
		return this.location_;
	}

	@Override
	public boolean canTravel() {
		return true;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Patient)
			return ((Patient)o).name.equals(name);
		return false;
	}
	
	/**
	 * @return True if d is a valid name.
	 */
	private boolean isValidName(String n) {
		return !n.equals("");
	}

}
