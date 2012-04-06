package scheduler.requirements;

import scheduler.HospitalDate;
import system.Location;
import warehouse.Warehouse;

/**
 * Class representing a requirement for a description.
 */
public interface Requirement
{
	/**
	 * @return The amount needed of a specific type.
	 */
	public int getAmount();

	/**
	 * Checks whether the given Requirable can meet this Requirement.
	 * 
	 * @param requirable
	 *            The requirable that has to be checked.
	 * @return True if the given Requirable can meet this Requirement.
	 */
	public boolean isMetBy(Requirable requirable);

	/**
	 * @return True if the Requirement needs a Machine or a SchedulableUser.
	 *         This Requirement can only be met when the admin adds Machines or
	 *         SchedulableUsers to the Hospital. If this Requirement is not met,
	 *         the Task can never be scheduled in this Hospital with the current
	 *         resources.
	 */
	public boolean isCrucial();

	/**
	 * Checks whether this Requirement is met on a specific HospitalDate and
	 * Location.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate that has to be checked.
	 * @param location
	 *            The Location that has to be checked.
	 * @return True if the Requirement is met on the given HospitalDate on the
	 *         given Location.
	 */
	public boolean isMetOn(HospitalDate hospitalDate, Location location);

	/**
	 * Deletes items this Requirement needs from the given Warehouse.
	 * 
	 * @param warehouse
	 *            The Warehouse where items will be deleted from.
	 */
	public void collect(Warehouse warehouse);

	/**
	 * @return True if this Requirement demands to be scheduled back to back.
	 */
	public boolean backToBack();

	/**
	 * @return True if the Requirement is 
	 */
	public boolean isMarkedForDeletion();
}