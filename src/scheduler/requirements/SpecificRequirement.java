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
	private boolean backToBack_;

	/**
	 * Default constructor.
	 * 
	 * @param requirable
	 *            The Requirable that is needed to satisfy this Requirement.
	 * @param backToBack
	 *            Boolean to set whether the Requirement must be scheduled back
	 *            to back.
	 */
	public SpecificRequirement(Requirable requirable, boolean backToBack) {
		requirable_ = requirable;
		backToBack_ = backToBack;
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
	 * @return True if this Requirement demands to be scheduled back to back.
	 */
	@Override
	public boolean backToBack() {
		return this.backToBack_;
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