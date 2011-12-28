package users;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import treatment.Medication;
import warehouse.Meal;
import warehouse.Warehouse;
import exceptions.InvalidAmountException;
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
		this.updateMeals(newTime);

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
	 */
	private void updateMeals(HospitalDate newTime) {
		int amountOfPatients = this.pfm.amountOfActivePatients();
		int amountOfMealsEaten = 0;
		HospitalDate nextDate = new HospitalDate(this.warehouse.getPreviousDate());
		long nextMeals[] = new long[3];
		int amountOfOrdersToPlace = 0;

		 do {
			amountOfOrdersToPlace++;
			 nextMeals = this.timeToNextMeals(nextDate);
			for (int i = 0; i < nextMeals.length; i++) {
				if (nextMeals[i] < 24)
					amountOfMealsEaten++;
			}
			nextDate = new HospitalDate(nextDate.getTimeSinceStart()
					+ HospitalDate.ONE_DAY);
		} while (nextDate.before(newTime));
		
		 if(newTime.getHour() < 23 && newTime.getMinute() < 59)
			 amountOfOrdersToPlace--;
		 		 
		 
		amountOfMealsEaten *= amountOfPatients;
		try {
			this.warehouse.eatMeals(amountOfMealsEaten);
		} catch (MealException e) {
			System.out.println(e.getMessage());
		} catch (InvalidAmountException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * @return The time till the next 3 meals
	 */
	private long[] timeToNextMeals(HospitalDate nextDate) {
		long rv[] = new long[3];
		long mealtimes[] = { 8, 12, 18 };

		for (int i = 0; i < rv.length; i++) {
			if (nextDate.getHour() > mealtimes[i]) // meal is next day
				rv[i] = nextDate.getHour() + 24 - mealtimes[i];
			else
				rv[i] = nextDate.getHour() - mealtimes[i];
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
		
	}
}