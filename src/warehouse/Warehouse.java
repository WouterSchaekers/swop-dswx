package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.HospitalDate;
import treatment.Medication;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;
import exceptions.InvalidHospitalDateException;

/**
 * This class represents a warehouse. It keeps track of all resources in the
 * hospital including food, plaster, medication,...
 */
public class Warehouse
{
	private final static int MAX_UNITS_OF_PLASTER = 8;
	private final static int MAX_UNITS_OF_MEDICATION = 10;
	private final static int MAX_UNITS_OF_MEALS = 120;
	private int unitsOfPlaster;
	private Collection<Medication> medication;
	private Collection<Meal> meals;

	private Collection<WarehouseItem> reserved;
	private HospitalDate prevDate;

	/**
	 * Default constructor. Fields will be initialised. Also the warehouse stock
	 * will be filled up completely with everything.
	 * 
	 * @param startDate
	 *            The date that this warehouse starts existing on. Will be used
	 *            to calculate the difference in time between the new system
	 *            time and the current system time, should the time advance.
	 * @throws InvalidHospitalDateException
	 */
	public Warehouse(HospitalDate startDate)
			throws InvalidHospitalDateException {
		medication = new ArrayList<Medication>();
		meals = new ArrayList<Meal>();
		reserved = new ArrayList<WarehouseItem>();
		this.unitsOfPlaster = MAX_UNITS_OF_PLASTER;
		for (int i = 0; i < MAX_UNITS_OF_MEALS; i++) {
			this.meals.add(new Meal(null));
		}
		this.medication.add(new ActivatedCarbon(false, null));
		this.medication.add(new Aspirin(false, null));
		this.medication.add(new Misc(false, null));
		this.medication.add(new SleepingTablets(false, null));
		this.medication.add(new Vitamins(false, null));
		this.medication.add(new ActivatedCarbon(false, null));
		this.medication.add(new Aspirin(false, null));
		this.medication.add(new Misc(false, null));
		if (!this.canHaveAsDate(startDate))
			throw new InvalidHospitalDateException(
					"Invalid date given to Warehouse!");
		this.prevDate = startDate;
	}

	/**
	 * @return If d is a valid hospital date for this warehouse.
	 */
	private boolean canHaveAsDate(HospitalDate d) {
		return d != null
				&& d.after(new HospitalDate(HospitalDate.START_OF_TIME));
	}

	/**
	 * This method will add plaster to this warehouse.
	 * 
	 * @param units
	 *            The amount of units to add to the warehouse.
	 * @throws WarehouseException
	 */
	public void addPlaster(int units) throws WarehouseException {
		if (!isValidAmountOfPlasterToAdd(units)) {
			throw new WarehouseException(
					"Either an invalid amount of plaster was given to this method, or either adding the given amount will result in an overflow of the warehouse. ");
		}
		this.unitsOfPlaster += units;
	}

	/**
	 * @return True if amount is a valid amount of plaster to add to this
	 *         Warehouse.
	 */
	private boolean isValidAmountOfPlasterToAdd(int amount) {
		return amount >= 0
				&& this.unitsOfPlaster + amount > MAX_UNITS_OF_PLASTER;
	}

	/**
	 * This method will add medication to this hospital.
	 * 
	 * @param medication
	 *            The medication to add to the hospital.
	 * @throws WarehouseException
	 */
	public void addMedication(Collection<Medication> medication)
			throws WarehouseException {
		if (!isValidAmountOfMedicationToAdd(medication.size())) {
			throw new WarehouseException(
					"Either an invalid amount of plaster was given to this method, or either adding the given amount will result in an overflow of the warehouse.");
		}
		this.medication.addAll(medication);
	}

	/**
	 * @return True if amount is a valid amount of Medication to add to this
	 *         Warehouse.
	 */
	private boolean isValidAmountOfMedicationToAdd(int amount) {
		return amount >= 0
				&& this.medication.size() + medication.size() > MAX_UNITS_OF_MEDICATION;
	}

	/**
	 * This method allows one to reserve an item to the Warehouse.
	 * 
	 * @param i
	 *            The item to add
	 */
	public void reserveItem(WarehouseItem i) {
		if (removeAndReserveFrom(i, meals))
			return;
		if (removeAndReserveFrom(i, medication))
			return;
	}

	/**
	 * Removes and reserves an item from the warehouse if possible.
	 * 
	 * @param i
	 *            The item to remove.
	 * @param t
	 *            The collection from which to remove it from.
	 * @return True if the element was removed, false if it wasn't.
	 */
	private <T extends WarehouseItem> boolean removeAndReserveFrom(
			WarehouseItem i, Collection<T> t) {
		if (t.contains(i)) {
			t.remove(i);
			reserved.add(i);
			return true;
		}
		return false;
	}

	/**
	 * This method will add meals to this hospital.
	 * 
	 * @param meals
	 *            The meals to add to the hospital.
	 * @throws WarehouseOverCapacityException
	 */
	public void addMeals(Collection<Meal> meals)
			throws WarehouseOverCapacityException {
		if (this.meals.size() + meals.size() > MAX_UNITS_OF_MEALS) {
			throw new WarehouseOverCapacityException(
					"There are too many meals.");
		}
		this.meals.addAll(meals);
	}

	/**
	 * This method should be called whenever there's a change in time. The
	 * warehouse will then update its stock accordingly.
	 * 
	 * @param newDate
	 *            The new system time.
	 */
	public void update(HospitalDate newDate) {
		long timeDiff = this.prevDate.getTimeBetween(newDate);
		int amountOfDays = (int) (timeDiff % (24 * HospitalDate.ONE_HOUR));
		int amountOfMealsADay = 3;

		for (int i = 0; i < amountOfDays; i++) {

			// remove the expired medication and order new things.
			for (int j = 0; j < amountOfMealsADay; j++) {
				// remove the eaten meals and add new ones at the end of the
				// day.
			}
		}

	}

	public boolean hasPlaster(int plaster) {
		return this.unitsOfPlaster >= plaster;
	}

	public boolean hasMedication(int plaster) {
		return this.unitsOfPlaster >= plaster;
	}
}
