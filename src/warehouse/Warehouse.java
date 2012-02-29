package warehouse;

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
	public final int MAX_UNITS_OF_PLASTER;
	public final int MAX_UNITS_OF_MEDICATION;
	public final int MAX_UNITS_OF_MEALS;
	private LinkedList<Medication> medication;
	private LinkedList<Plaster> plaster;
	private LinkedList<Meal> meals;
	private HospitalDate curDate;

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
		this.MAX_UNITS_OF_PLASTER = 8;
		this.MAX_UNITS_OF_MEDICATION = 10;
		this.MAX_UNITS_OF_MEALS = 120;
		medication = new LinkedList<Medication>();
		plaster = new LinkedList<Plaster>();
		meals = new LinkedList<Meal>();
		for (int i = 0; i < this.MAX_UNITS_OF_PLASTER; i++) {
			this.plaster.add(new Plaster());
		}
		for (int i = 0; i < this.MAX_UNITS_OF_MEALS; i++) {
			this.meals.add(new Meal(startDate));
		}
		this.medication.add(new ActivatedCarbon(false, startDate));
		this.medication.add(new Aspirin(false, startDate));
		this.medication.add(new Misc(false, startDate));
		this.medication.add(new SleepingTablets(false, startDate));
		this.medication.add(new Vitamins(false, startDate));
		this.medication.add(new ActivatedCarbon(false, startDate));
		this.medication.add(new Aspirin(false, startDate));
		this.medication.add(new Misc(false, startDate));
		if (!this.canHaveAsDate(startDate))
			throw new InvalidHospitalDateException(
					"Invalid date given to Warehouse!");
		this.curDate = startDate;
	}

	/**
	 * @return If d is a valid hospital date for this warehouse.
	 */
	private boolean canHaveAsDate(HospitalDate d) {
		return d != null
				&& (curDate == null || d.after(new HospitalDate(
						HospitalDate.START_OF_TIME)));
	}

	public void addPlaster(Plaster plaster) throws WarehouseException {
		if (!isValidAmountOfPlasterToAdd(1)) {
			throw new WarehouseException(
					"Either an invalid amount of plaster was given to this method, or either adding the given amount will result in an overflow of the warehouse. ");
		}
		this.plaster.add(plaster);
		this.notifyObservers();
	}

	/**
	 * This method will add plaster to this warehouse.
	 * 
	 * @param units
	 *            The amount of units to add to the warehouse.
	 * @throws WarehouseException
	 */
	public void addPlaster(Collection<Plaster> plaster)
			throws WarehouseException {
		if (!isValidAmountOfPlasterToAdd(plaster.size())) {
			throw new WarehouseException(
					"Either an invalid amount of plaster was given to this method, or either adding the given amount will result in an overflow of the warehouse. ");
		}
		this.plaster.addAll(plaster);
		this.notifyObservers();
	}

	/**
	 * @return True if amount is a valid amount of plaster to add to this
	 *         Warehouse.
	 */
	private boolean isValidAmountOfPlasterToAdd(int amount) {
		return amount >= 0
				&& this.plaster.size() + amount <= MAX_UNITS_OF_PLASTER;
	}

	public boolean hasPlaster(int plaster) {
		return this.plaster.size() >= plaster;
	}

	public int amountOfPlaster() {
		return this.plaster.size();
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

	public void addMedication(Medication medication) throws WarehouseException {
		if (!isValidAmountOfMedicationToAdd(1)) {
			throw new WarehouseException(
					"Either an invalid amount of plaster was given to this method, or either adding the given amount will result in an overflow of the warehouse.");
		}
		this.medication.add(medication);
		this.notifyObservers();
	}

	public LinkedList<Medication> getMedication() {
		return new LinkedList<Medication>(this.medication);
	}

	public int amountOfMedication() {
		return this.medication.size();
	}

	/**
	 * @return True if amount is a valid amount of Medication to add to this
	 *         Warehouse.
	 */
	private boolean isValidAmountOfMedicationToAdd(int amount) {
		return amount >= 0
				&& this.medication.size() + amount <= MAX_UNITS_OF_MEDICATION;
	}

	public boolean hasMedication(MedicationType medicationType, int amount) {
		return this.medication.size() >= this.getMedicationList(medicationType)
				.size();
	}

	public Medication getMedication(MedicationType medicationType)
			throws WarehouseException {
		LinkedList<Medication> medicationList = this
				.getMedicationList(medicationType);
		if (medicationList.size() == 0) {
			throw new WarehouseException("The medication of type "
					+ medicationType.toString() + " is not available anymore.");
		}
		return this.getMedicationList(medicationType).get(0);
	}

	public int medicationOfThisType(MedicationType medicationType) {
		return this.getMedicationList(medicationType).size();
	}

	private LinkedList<Medication> getMedicationList(
			MedicationType medicationType) {
		LinkedList<Medication> medicationList = new LinkedList<Medication>();
		for (int i = 0; i < this.medication.size(); i++) {
			Medication curMedication = this.medication.get(i);
			if (curMedication.medicationType.equals(medicationType)) {
				medicationList.add(curMedication);
			}
		}
		return medicationList;
	}

	public void removeMedication(Medication medication)
			throws WarehouseException {
		if (this.medication.contains(medication)) {
			throw new WarehouseException("Medication cannot be removed.");
		}
		this.medication.remove(medication);
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
		this.meals.addAll(meals);
		this.notifyObservers();
	}

	public void addMeal(Meal meal) throws WarehouseOverCapacityException {
		this.meals.add(meal);
		this.notifyObservers();
	}

	public LinkedList<Meal> getMeals() {
		return new LinkedList<Meal>(this.meals);
	}

	public boolean hasMeals(int meals) {
		return this.amountOfMeals() >= meals;
	}

	/**
	 * Eats amount amount of meals from this warehouse.
	 * 
	 * @throws MealException
	 * @throws InvalidAmountException
	 */
	public void eatMeals(int amount) throws MealException,
			InvalidAmountException {
		if (amount > 0) {
			if (amount < meals.size()) {
				for (int i = 0; i < amount; i++) {
					meals.remove(0);
				}
			} else {
				this.meals = new LinkedList<Meal>();
				throw new MealException(
						"The warehouse ran out of meals! The people are hungry!");
			}
		} else {
			throw new InvalidAmountException(
					"Invalid amount of meals given to warehouse!");
		}
		this.notifyObservers();
	}

	public int amountOfMeals() {
		return this.meals.size();
	}

	public void removeMeal(Meal meal) throws WarehouseException {
		if (this.meals.contains(meal)) {
			throw new WarehouseException("Meal cannot be removed.");
		}
		this.meals.remove(meal);
	}

	@Basic
	public HospitalDate getDate() {
		return this.curDate;
	}

	@Basic
	public void setDate(HospitalDate newDate) {
		this.curDate = newDate;
	}

	// public LinkedList<Meal> getMeals() {
	// return new LinkedList<Meal>(this.meals);
	// }

	// private void setPlaster(int newPlasterAmount) {
	// this.unitsOfPlaster = newPlasterAmount;
	// this.notifyObservers();
	// }
	//
	// private void setMedicaton(LinkedList<Medication> newMedication) {
	// this.medication = newMedication;
	// }
	//
	// private void setMeals(LinkedList<Meal> newMeals) {
	// this.meals = new LinkedList<Meal>(newMeals);
	// }
}