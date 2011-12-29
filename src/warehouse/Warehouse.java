package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import scheduler.HospitalDate;
import treatment.Medication;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import exceptions.MealException;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents a warehouse. It keeps track of all resources in the
 * hospital including food, plaster, medication,...
 */
public class Warehouse extends Observable
{
	public static final int MAX_UNITS_OF_PLASTER = 8;
	public static final int MAX_UNITS_OF_MEDICATION = 10;
	public static final int MAX_UNITS_OF_MEALS = 120;
	private int unitsOfPlaster;
	private LinkedList<Medication> medication;
	private LinkedList<Meal> meals;

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
		medication = new LinkedList<Medication>();
		meals = new LinkedList<Meal>();
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
		this.notifyObservers();
	}

	/**
	 * @return True if amount is a valid amount of plaster to add to this
	 *         Warehouse.
	 */
	private boolean isValidAmountOfPlasterToAdd(int amount) {
		return amount >= 0
				&& this.unitsOfPlaster + amount <= MAX_UNITS_OF_PLASTER;
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
		this.notifyObservers();
	}

	/**
	 * @return True if amount is a valid amount of Medication to add to this
	 *         Warehouse.
	 */
	private boolean isValidAmountOfMedicationToAdd(int amount) {
		return amount >= 0
				&& this.medication.size() + medication.size() <= MAX_UNITS_OF_MEDICATION;
	}

	/**
	 * This method allows one to reserve an item to the Warehouse.
	 * 
	 * @param i
	 *            The item to add
	 */
	public void reserveItem(WarehouseItem i) {
		if (removeAndReserveFrom(i, meals)){
			this.notifyObservers();
			return;
		}
		if (removeAndReserveFrom(i, medication)){
			this.notifyObservers();
			return;
		}
	}

	/**
	 * Eats amount amount of meals from this warehouse.
	 * 
	 * @throws MealException
	 * @throws InvalidAmountException 
	 */
	public void eatMeals(int amount) throws MealException, InvalidAmountException {
		LinkedList<Meal> newMeals = new LinkedList<Meal>();
		if (amount > 0) {
			if (amount < meals.size()) {
				for (int i = amount; i < meals.size(); i++) {
					newMeals.add(this.meals.get(i));
				}
			} else {
				this.meals = newMeals;
				throw new MealException("The warehouse ran out of meals! The people are hungry!");
			}
			this.meals = newMeals;
			
		} else {
			throw new InvalidAmountException("Invalid amount of meals given to warehouse!");
		}
		this.notifyObservers();
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
			this.notifyObservers();
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
		this.notifyObservers();
	}

	public boolean hasPlaster(int plaster) {
		return this.unitsOfPlaster >= plaster;
	}

	public boolean hasMedication(int plaster) {
		return this.unitsOfPlaster >= plaster;
	}

	@Basic
	public HospitalDate getPreviousDate() {
		return this.prevDate;
	}

	@Basic
	public void setPreviousDate(HospitalDate newDate) {
		this.prevDate = newDate;
	}

	public int getPlaster() {
		return this.unitsOfPlaster;
	}

	public LinkedList<Medication> getMedication() {
		return new LinkedList<Medication>(this.medication);
	}

	public LinkedList<Meal> getMeals() {
		return new LinkedList<Meal>(this.meals);
	}

//	private void setPlaster(int newPlasterAmount) {
//		this.unitsOfPlaster = newPlasterAmount;
//		this.notifyObservers();
//	}
//
//	private void setMedicaton(LinkedList<Medication> newMedication) {
//		this.medication = newMedication;
//	}
//
//	private void setMeals(LinkedList<Meal> newMeals) {
//		this.meals = new LinkedList<Meal>(newMeals);
//	}

	public int amountOfMeals() {
		return this.meals.size();
	}
}
