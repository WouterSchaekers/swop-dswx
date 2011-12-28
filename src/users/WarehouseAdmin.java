package users;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import treatment.Medication;
import warehouse.Meal;
import warehouse.Warehouse;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidNameException;
import exceptions.MealException;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents the administrator of the warehouse that is in the
 * hospital.
 */
public class WarehouseAdmin extends User
{
	private Warehouse warehouse;
	private PatientFileManager pfm;
	public Observable observable;

	/**
	 * Default constructor. Will appoint this admin his warehouse.
	 * 
	 * @param depot
	 *            The warehouse of this warehouse admin.
	 * @param pfm
	 *            The patientfile manager where this warehouse admin should get
	 *            the amount of active patients in its hospital.
	 * @throws InvalidNameException
	 */
	public WarehouseAdmin(Warehouse depot, PatientFileManager pfm)
			throws InvalidNameException {
		super("The Warehouse administrator");
		this.warehouse = depot;
		this.pfm = pfm;
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
	public void update(HospitalDate newTime) {
		this.removeExpiredItems(newTime);
		try {
			this.updateMeals(newTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.warehouse.setPreviousDate(newTime);
	}

	/**
	 * Removes the expired items (note meals!)from the warehouse that is
	 * associated with this warehouse administrator.
	 */
	private void removeExpiredItems(HospitalDate newTime) {
		LinkedList<Medication> meds = this.warehouse.getMedication();
		LinkedList<Medication> newMeds = new LinkedList<Medication>();
		for (Medication m : meds) {
			if (!m.hasPassedDate(newTime))
				newMeds.add(m);
		}
		this.warehouse.setMedicaton(newMeds);
	}

	/**
	 * Updates the amount of meals after 1 meal. Also
	 * 
	 * @throws InvalidHospitalDateArgument
	 * @throws InvalidAmountException
	 * @throws MealException
	 */
	private void updateMeals(HospitalDate newTime)
			throws InvalidHospitalDateArgument, MealException,
			InvalidAmountException {
		int amountOfPatients = this.pfm.amountOfActivePatients();
		int amountOfMealsEaten = this.amountOfMealsTill(newTime)
				* amountOfPatients;
		this.warehouse.eatMeals(amountOfMealsEaten);
		this.orderMoreMeals(newTime);

	}

	// TODO
	private void updateOrders(HospitalDate newDate) {

	}

	/**
	 * @return The time till the next meal.
	 * @throws InvalidHospitalDateArgument
	 */
	private long timeToNextMeal(HospitalDate nextDate)
			throws InvalidHospitalDateArgument {
		HospitalDate[] meals = {
				new HospitalDate(nextDate.getYear(), nextDate.getMonth(),
						nextDate.getDay(), 8, 0, 0),
				new HospitalDate(nextDate.getYear(), nextDate.getMonth(),
						nextDate.getDay(), 12, 0, 0),
				new HospitalDate(nextDate.getYear(), nextDate.getMonth(),
						nextDate.getDay(), 18, 0, 0) };
		HospitalDate breakfastNextDay = new HospitalDate(
				meals[0].getTimeSinceStart() + HospitalDate.ONE_DAY);
		for (int i = 0; i < meals.length; i++) {
			if (meals[i].after(nextDate))
				return nextDate.getTimeBetween(meals[i]);
		}
		// if breakfast, lunch and dinner have already passed, the next meal
		// will be tomorrow and breakfast.
		return nextDate.getTimeBetween(breakfastNextDay);
	}

	/**
	 * @return The amount of meals between the prev date of warehouse and
	 *         newTime.
	 * @throws InvalidHospitalDateArgument
	 */
	private int amountOfMealsTill(HospitalDate newTime)
			throws InvalidHospitalDateArgument {
		int amountOfMeals = 0;
		HospitalDate nextDate = new HospitalDate(
				this.warehouse.getPreviousDate());
		long nextMealTime = 0;

		while (nextDate.before(newTime)) {
			nextMealTime = this.timeToNextMeal(nextDate);
			nextDate = new HospitalDate(nextDate.getTimeSinceStart()
					+ nextMealTime);
			if (nextDate.before(newTime))
				amountOfMeals++;
		}
		return amountOfMeals;
	}

	/**
	 * @return The amount of orders between the prev date of warehouse and
	 *         newTime.
	 * @throws InvalidHospitalDateArgument
	 */
	private int amountOfOrdersTill(HospitalDate lastDate)
			throws InvalidHospitalDateArgument {
		HospitalDate nextDate = new HospitalDate(
				this.warehouse.getPreviousDate());
		HospitalDate orderTime = new HospitalDate(nextDate.getYear(),
				nextDate.getMonth(), nextDate.getDay(), 23, 59, 00);
		int rv = 0;
		while (nextDate.before(lastDate)) {
			rv++;
			orderTime = new HospitalDate(orderTime.getTimeSinceStart()
					+ HospitalDate.ONE_DAY);
		}
		return rv;
	}

	/**
	 * Places the order for more meals should the time be updated.
	 * 
	 * @param newTime
	 *            The new system time.
	 */
	private void orderMoreMeals(HospitalDate newTime) {
		// TODO
	}
}