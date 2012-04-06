package patient;

import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.LocationTimeTable;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import system.Location;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.PatientIN;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;

/**
 * This class represents a patient. Patients are linked to any number of patient
 * files depending on which hospital they go to. Patients have TimeTables which
 * means they are Schedulable.
 */
public class Patient implements Schedulable, PatientIN
{
	private LocationTimeTable locationTimeTable_;
	private String name;
	private TimeTable timeTable_;

	/**
	 * Default constructor. Initialises a new Patient object.
	 * 
	 * @param name
	 *            The name of the patient.
	 * @throws InvalidNameException
	 *             If the given name is not a valid name for a new patient.
	 */
	Patient(String name) throws InvalidNameException {
		if (!isValidName(name))
			throw new InvalidNameException("Invalid patient name given to Patient constructor!");
		this.name = name;
		this.timeTable_ = new TimeTable();
		this.locationTimeTable_ = new LocationTimeTable();
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate) {
		return timeTable_.hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public boolean canTravel() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Patient)
			return ((Patient) o).name.equals(name);
		return false;
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException {
		return timeTable_.getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	@Override
	public Location getLocationAt(HospitalDate hospitalDate) {
		return locationTimeTable_.getLocationAt(hospitalDate);
	}

	@Override
	@Basic
	public String getName() {
		return this.name;
	}

	@Override
	@Basic
	public TimeTable getTimeTable() {
		return this.timeTable_;
	}

	/**
	 * @return True if d is a valid name.
	 */
	private boolean isValidName(String n) {
		return !n.equals("");
	}

	@Override
	public boolean mustBeBackToBack() {
		return false;
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot, Location location) throws InvalidSchedulingRequestException {
		this.timeTable_.addTimeSlot(timeSlot);
		LocationTimeSlot slot = new LocationTimeSlot(timeSlot, location);
		this.locationTimeTable_.addLocationTimeSlot(slot);
	}

	@Override
	public void updateTimeTable(HospitalDate newDate) {
		timeTable_.updateTimeTable(newDate);
	}

}
