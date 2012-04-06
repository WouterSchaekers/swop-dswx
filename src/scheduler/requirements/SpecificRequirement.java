package scheduler.requirements;

import scheduler.HospitalDate;
import system.Location;
import warehouse.Warehouse;

/**
 * Class representing a Requirement for a description.
 */
public class SpecificRequirement implements Requirement
{
	private Requirable requirable_;

	/**
	 * Default constructor.
	 * 
	 * @param requirable
	 *            The Requirable that is needed to satisfy this Requirement.
	 */
	public SpecificRequirement(Requirable requirable) {
		requirable_ = requirable;
	}

	/**
	 * Checks whether the given Requirable can meet this Requirement.
	 * 
	 * @param requirable
	 *            The requirable that has to be checked.
	 * @return True if the given Requirable can meet this Requirement.
	 */
	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable_.equals(requirable);
	}

	/**
	 * @return True.
	 */
	@Override
	public boolean isCrucial() {
		return true;
	}

	/**
	 * Checks whether this Requirement is met on a specific HospitalDate and
	 * Location.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate that has to be checked.
	 * @param location
	 *            The Location that has to be checked.
	 * @return False.
	 */
	@Override
	public boolean isMetOn(HospitalDate hospitalDate, Location location) {
		return false;
	}

	/**
	 * No op.
	 */
	@Override
	public void collect(Warehouse warehouse) {
		;
	}

	/**
	 * @return 1.
	 */
	public int getAmount() {
		return 1;
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isMarkedForDeletion() {
		return false;
	}
}