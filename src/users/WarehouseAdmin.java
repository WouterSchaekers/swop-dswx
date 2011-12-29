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
import external.StockOrder;

/**
 * This class represents the administrator of the warehouse that is in the
 * hospital.
 */
public class WarehouseAdmin extends User
{
	private Warehouse warehouse;
	private PatientFileManager pfm;
	public Observable myObservable;

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
			this.orderMoreMeds();
		} catch (InvalidHospitalDateArgument e1) {
			System.out.println(e1.getMessage());
		}
		try {
			this.updateMeals(newTime);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.warehouse.setPreviousDate(newTime);
	}

	/**
	 * Will remove all expired medication from the warehouse, given that the new
	 * system time is newTime.
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
	 * Will update the amount of meals stored in the warehouse after the system
	 * time has changed.
	 * 
	 * @param newTime
	 *            The new system time.
	 * @throws InvalidHospitalDateArgument
	 * @throws MealException
	 * @throws InvalidAmountException
	 */
	private void updateMeals(HospitalDate newTime)
			throws InvalidHospitalDateArgument, MealException,
			InvalidAmountException {
		HospitalDate prevDate = this.warehouse.getPreviousDate();
		HospitalDate nextDate = new HospitalDate(prevDate.getTimeSinceStart() + timeToNextMeal(prevDate));
		HospitalDate orderTime;
		
		while (nextDate.before(newTime)) {
			this.removeExpiredMeals(nextDate);
			this.eatMeals();
			
			orderTime = new HospitalDate(prevDate.getYear(), prevDate.getMonth(), prevDate.getDay(), 23, 59, 00);
			if(nextDate.after(orderTime))
				this.orderMoreMeals(nextDate);
			
			prevDate = nextDate;
			nextDate = new HospitalDate(prevDate.getTimeSinceStart() + timeToNextMeal(prevDate));
		}
		
	}
	
	/**
	 * Will remove the meals that will have expired at expDate.
	 * 
	 * @param expDate
	 *            The date on which one would like to remove expired meals.
	 */
	private void removeExpiredMeals(HospitalDate expDate) {
		LinkedList<Meal> mealCol = this.warehouse.getMeals();
		LinkedList<Meal> newMealCol = new LinkedList<Meal>();
		for(Meal m : mealCol) {
			if(!m.hasPassedDate(expDate))
				newMealCol.add(m);
		}
		this.warehouse.setMeals(newMealCol);
	}

	/**
	 * Will eat the needed amount of meals for 1 meal time.
	 * @throws InvalidHospitalDateArgument
	 * @throws InvalidAmountException 
	 * @throws MealException 
	 */
	private void eatMeals() throws MealException, InvalidAmountException {
		int amountOfPatients = this.pfm.amountOfActivePatients();
		this.warehouse.eatMeals(amountOfPatients);
	}
	
	/**
	 * Will order more meals at the end of a day.
	 * 
	 * @throws InvalidHospitalDateArgument
	 */
	private void orderMoreMeals(HospitalDate newTime) throws InvalidHospitalDateArgument {

		int amountOfMealsToOrder = 15 + this.pfm.amountOfActivePatients() * 3 * 2 - this.warehouse.amountOfMeals();
		StockOrder s = new StockOrder(amountOfMealsToOrder);
		// TODO: fix import en associatie naar stock importer.
	}
	
	/**
	 * Will order more meds.
	 * 
	 * @throws InvalidHospitalDateArgument
	 */
	private void orderMoreMeds() throws InvalidHospitalDateArgument {
		LinkedList<Medication> oldMeds = this.warehouse.getMedication();
		StockOrder[] s = new StockOrder[2];
		if(oldMeds.size() < Warehouse.MAX_UNITS_OF_MEDICATION/2) {
			s[0] = new StockOrder(Warehouse.MAX_UNITS_OF_MEDICATION - oldMeds.size());
		}
		s[1] = new StockOrder(Warehouse.MAX_UNITS_OF_PLASTER - this.warehouse.getPlaster());
		// TODO: fix import en associatie naar stock importer.
	}
	
	/**
	 * @return The amount of millis till the next meal takes place.
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
}