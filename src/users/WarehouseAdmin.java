package users;

import java.util.Collection;
import scheduler.HospitalDate;
import treatment.Medication;
import warehouse.Meal;
import warehouse.Warehouse;
import exceptions.InvalidNameException;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents the administrator of the warehouse that is in the
 * hospital.
 */
public class WarehouseAdmin extends User
{
	private Warehouse warehouse;

	/**
	 * Default constructor. Will appoint this admin his warehouse.
	 * 
	 * @param depot
	 *            The warehouse of this warehouse admin.
	 * @throws InvalidNameException
	 */
	public WarehouseAdmin(Warehouse depot) throws InvalidNameException {
		super("The Warehouse administrator");
		this.warehouse = depot;
	}

	/**
	 * This method adds plaster to the warehouse that has been assigned to this
	 * warehouse admin.
	 * 
	 * @param units
	 *            The amount of plaster to add to the warehouse.
	 * @throws WarehouseException
	 */
	public void addPlaster(int units) throws WarehouseException {
		warehouse.addPlaster(units);
	}

	/**
	 * This method adds medication to the warehouse that has been assigned to
	 * this warehouse admin.
	 * 
	 * @param units
	 *            The amount of medication to add to the warehouse.
	 * @throws WarehouseException
	 */
	public void addMedication(Collection<Medication> medication)
			throws WarehouseException {
		warehouse.addMedication(medication);
	}

	/**
	 * This method adds meals to the warehouse that has been assigned to this
	 * warehouse admin.
	 * 
	 * @param units
	 *            The amount of meals to add to the warehouse.
	 * @throws WarehouseOverCapacityException
	 */
	public void addMeals(Collection<Meal> meals)
			throws WarehouseOverCapacityException {
		warehouse.addMeals(meals);
	}

	/**
	 * This method should be called whenever there's a change in time. The
	 * warehouse will then update its stock accordingly.
	 * 
	 * @param newTime
	 *            The new system time.
	 */
	public void updateWarehouse(HospitalDate newTime) {
		HospitalDate prevDate = this.warehouse.getPreviousDate();
		long timeDiff = prevDate.getTimeBetween(newTime);
		int mealsADay = 3;
		int amountOfMeals = (int) (timeDiff / (24 * HospitalDate.ONE_HOUR)) * mealsADay;
		
		this.removeExpiredItems(newTime);
		
		for (int i = 0; i < amountOfMeals; i++) {
			this.updateMeals();
		}
		
		this.warehouse.setPreviousDate(newTime);
	}

	/**
	 * Removes the expired items (note meals!)from the warehouse that is
	 * associated with this warehouse administrator.
	 */
	public void removeExpiredItems(HospitalDate newTime) {
		Collection<Medication> meds = this.warehouse.getMedication();
		int plaster = this.warehouse.getPlaster();

	}

	/**
	 * Updates the amount of meals after 1 meal. Also 
	 */
	public void updateMeals() {
		
	}

	/**
	 * @return True if u is a valid amount of units for whatever kind of thing
	 *         one would like to add to the warehouse of this warehouse
	 *         administrator.
	 */
	public static boolean isValidAmountOfUnits(int u) {
		return u >= 0;
	}

	/**
	 * @return The time till the next 3 meals
	 */
	private long[] timeToNextMeals() {
		long rv[] = new long[3];
		long breakfast = HospitalDate.ONE_HOUR * 8;
		long lunch = HospitalDate.ONE_HOUR * 12;
		long dinner = HospitalDate.ONE_HOUR * 8;
		return null;
	}
}