package users;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFileManager;
import scheduler.DummyDate;
import scheduler.HospitalDate;
import treatment.Medication;
import warehouse.ActivatedCarbonType;
import warehouse.AspirinType;
import warehouse.Meal;
import warehouse.MealOrder;
import warehouse.MedicationOrder;
import warehouse.MedicationType;
import warehouse.MiscType;
import warehouse.Plaster;
import warehouse.PlasterOrder;
import warehouse.SleepingTabletsType;
import warehouse.StockProvider;
import warehouse.VitaminsType;
import warehouse.Warehouse;
import controllers.interfaces.WarehouseAdminIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidNameException;
import exceptions.MealException;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents the administrator of the warehouse that is in the
 * hospital.
 */
public class WarehouseAdmin extends User implements WarehouseAdminIN
{
	private Warehouse warehouse;
	private PatientFileManager patientFileManager;
	private StockProvider stockProvider;
	private boolean orderedPlaster;
	private boolean orderedMedication;
	private int plasterExpected;
	private int medicationExpected;
	private int mealsExpected;

	/**
	 * Default constructor. Will appoint this admin his warehouse.
	 * 
	 * @param depot
	 *            The warehouse of this warehouse admin.
	 * @param patientFileManager
	 *            The patientfile manager where this warehouse admin should get
	 *            the amount of active patients in its hospital.
	 * @throws InvalidNameException
	 */
	public WarehouseAdmin(Warehouse warehouse, StockProvider stockProvider,
			PatientFileManager patientFileManager) throws InvalidNameException {
		super("The Warehouse administrator");
		this.warehouse = warehouse;
		this.stockProvider = stockProvider;
		this.patientFileManager = patientFileManager;
		this.plasterExpected = 0;
		this.medicationExpected = 0;
		this.mealsExpected = 0;
	}

	/**
	 * This method adds plaster to the warehouse that has been assigned to this
	 * warehouse admin.
	 * 
	 * @param units
	 *            The amount of plaster to add to the warehouse.
	 * @throws WarehouseException
	 */
	public void addPlaster(Collection<Plaster> plaster) throws WarehouseException {
		warehouse.addPlaster(plaster);
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

	public void updateTime(HospitalDate newDate) throws MealException,
			InvalidAmountException, WarehouseException {
		this.removeExpiredMedication(newDate);
		this.advanceTime(newDate);
	}

	private void advanceTime(HospitalDate newDate) throws MealException,
			InvalidAmountException, WarehouseException {
		LinkedList<DummyDate> mealTimes = new LinkedList<DummyDate>();
		DummyDate breakfast = new DummyDate(8, 0, 0);
		DummyDate dinner = new DummyDate(12, 0, 0);
		DummyDate supper = new DummyDate(18, 0, 0);
		DummyDate updateMeals = new DummyDate(23, 59, 0);
		mealTimes.add(breakfast);
		mealTimes.add(dinner);
		mealTimes.add(supper);
		int amountOfPatients = this.patientFileManager.amountOfActivePatients();
		HospitalDate curDate = warehouse.getDate();
		while (curDate.before(newDate)) {
			boolean updated = false;
			for (int i = 0; i < mealTimes.size(); i++) {
				HospitalDate newCurDate = mealTimes.get(i)
						.combineWithHospitalDate(curDate);
				if (curDate.before(newCurDate)
						&& (newCurDate.before(newDate) || newCurDate
								.equals(newDate))) {
					this.removeExpiredMeals(newCurDate);
					warehouse.eatMeals(amountOfPatients);
					curDate = newCurDate;
					updated = true;
					break;
				}
			}
			if (!updated) {
				HospitalDate newCurDate = updateMeals
						.combineWithHospitalDate(curDate);
				if (curDate.before(newCurDate)
						&& (newCurDate.before(newDate) || newCurDate
								.equals(newDate))) {
					this.updateMeals(amountOfPatients);
					curDate = newCurDate;
				}
				newCurDate = new HospitalDate(curDate.getYear(),
						curDate.getMonth(), curDate.getDay() + 1, 0, 0, 0);
				if (newCurDate.before(newDate)) {
					curDate = newCurDate;
				} else {
					curDate = newDate;
				}
			}
		}
	}

	private void removeExpiredMeals(HospitalDate newDate)
			throws WarehouseException {
		LinkedList<Meal> meals = this.warehouse.getMeals();
		for (Meal m : meals) {
			if (m.hasPassedDate(newDate)) {
				this.warehouse.removeMeal(m);
			}
		}
	}

	private void removeExpiredMedication(HospitalDate newDate)
			throws WarehouseException {
		LinkedList<Medication> medication = this.warehouse.getMedication();
		for (Medication m : medication) {
			if (m.hasPassedDate(newDate)) {
				this.warehouse.removeMedication(m);
			}
		}
	}

	public void updateStock() {
		this.updatePlaster();
		this.updateMedication();
	}

	private void updatePlaster() {
		if (!this.orderedPlaster
				&& this.warehouse.amountOfPlaster() < this.warehouse.MAX_UNITS_OF_PLASTER) {
			this.orderPlaster(this.warehouse.MAX_UNITS_OF_PLASTER
					- this.warehouse.amountOfPlaster());
		}
	}

	private void orderPlaster(int amount) {
		this.orderedPlaster = true;
		this.plasterExpected = amount;
		while(amount-- > 0){
			this.stockProvider.orderPlaster(this);
		}
	}

	private void updateMedication() {
		if (!this.orderedMedication
				&& this.warehouse.amountOfMedication() < this.warehouse.MAX_UNITS_OF_MEDICATION / 2) {
			this.orderMedication(this.warehouse.MAX_UNITS_OF_MEDICATION
					- this.warehouse.amountOfMedication());
		}
	}

	private void orderMedication(int amount) {
		ActivatedCarbonType activatedCarbonType = new ActivatedCarbonType();
		AspirinType aspirinType = new AspirinType();
		MiscType miscType = new MiscType();
		SleepingTabletsType sleepingTabletsType = new SleepingTabletsType();
		VitaminsType vitaminsType = new VitaminsType();
		LinkedList<MedicationType> medicationTypes = new LinkedList<MedicationType>();
		medicationTypes.add(activatedCarbonType);
		medicationTypes.add(aspirinType);
		medicationTypes.add(miscType);
		medicationTypes.add(sleepingTabletsType);
		medicationTypes.add(vitaminsType);
		this.medicationExpected = amount;
		while (amount-- > 0) {
			this.orderMedication(medicationTypes.get((int) (Math.random() * 5)));
		}
		this.orderedMedication = true;
	}

	private void orderMedication(MedicationType medicationType) {
		this.stockProvider.orderMedication(this, medicationType);
	}

	private void updateMeals(int amountOfActivePatients) {
		int amountOfMealsToBeOrdered = 15 + 6 * amountOfActivePatients
				- this.warehouse.amountOfMeals() - this.mealsExpected;
		if (amountOfMealsToBeOrdered > this.warehouse.MAX_UNITS_OF_MEALS
				- (this.warehouse.amountOfMeals() + this.mealsExpected)) {
			amountOfMealsToBeOrdered = this.warehouse.MAX_UNITS_OF_MEALS
					- (this.warehouse.amountOfMeals() + this.mealsExpected);
		}
		this.orderMeals(amountOfMealsToBeOrdered);
	}

	private void orderMeals(int amount) {
		while(amount-- > 0){
			this.stockProvider.orderMeal(this);
		}
	}
	
	public void addPlasterOrder(PlasterOrder plasterOrder) throws WarehouseException{
		this.warehouse.addPlaster(plasterOrder.getStockItem());
	}
	
	public void addMedicationOrder(MedicationOrder medicationOrder) throws WarehouseException {
		this.warehouse.addMedication(medicationOrder.getStockItem());
	}
	
	public void addMealOrder(MealOrder mealOrder) throws WarehouseOverCapacityException{
		this.warehouse.addMeal(mealOrder.getStockItem());
	}
}