package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.HospitalDate;
import treatment.Medication;
import exceptions.DepotOverCapacityException;
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
	 * Default constructor. Fields will be initialised.
	 * 
	 * @param startDate
	 *            The date that this warehouse starts existing on. Will be used
	 *            to calculate the difference in time between the new system
	 *            time and the current system time, should the time advance.
	 * @throws InvalidHospitalDateException 
	 */
	public Warehouse(HospitalDate startDate) throws InvalidHospitalDateException{
		medication = new ArrayList<Medication>();
		meals = new ArrayList<Meal>();
		reserved = new ArrayList<WarehouseItem>();
		this.unitsOfPlaster = MAX_UNITS_OF_PLASTER;
		for(int i = 0; i < MAX_UNITS_OF_MEALS; i++){
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
		if(!this.canHaveAsDate(startDate))
			throw new InvalidHospitalDateException("Invalid date given to Warehouse!");
		this.prevDate = startDate;
	}
	
	/**
	 * @return If d is a valid hospital date for this warehouse.
	 */
	private boolean canHaveAsDate(HospitalDate d) {
		return d != null && d.after(new HospitalDate(HospitalDate.START_OF_TIME));
	}
	
	/**
	 * This method will add plaster to this warehouse.
	 * @param units
	 * The amount of units to add to the warehouse.
	 * @throws DepotOverCapacityException
	 */
	public void addPlaster(int units) throws DepotOverCapacityException{
		if(this.unitsOfPlaster + units > MAX_UNITS_OF_PLASTER){
			throw new DepotOverCapacityException("There is too much plaster.");
		}
		this.unitsOfPlaster += units;
	}
	
	/**
	 * This method will add medication to this hospital.
	 * @param medication
	 * The medication to add to the hospital.
	 * @throws DepotOverCapacityException
	 */
	public void addMedication(Collection<Medication> medication) throws DepotOverCapacityException{
		if(this.medication.size() + medication.size()> MAX_UNITS_OF_MEDICATION){
			throw new DepotOverCapacityException("There is too much medication.");
		}
		this.medication.addAll(medication);
	}
	
	/**
	 * This method allows one to reserve an item to the Warehouse.
	 * @param i
	 * The item to add
	 */
	public void reserveItem(WarehouseItem i) {
		if(removeAndReserveFrom(i, meals))
			return;
		if(removeAndReserveFrom(i, medication))
			return;		
	}
	
	private <T extends WarehouseItem> boolean removeAndReserveFrom(WarehouseItem i, Collection<T> t) {
		if(t.contains(i))
		{
			t.remove(i);
			reserved.add(i);
			return true;
		}
		return false;
	}
	
	/**
	 * This method will add meals to this hospital.
	 * @param meals
	 * The meals to add to the hospital.
	 * @throws DepotOverCapacityException
	 */
	public void addMeals(Collection<Meal> meals) throws DepotOverCapacityException{
		if(this.meals.size() + meals.size()> MAX_UNITS_OF_MEALS){
			throw new DepotOverCapacityException("There are too many meals.");
		}
		this.meals.addAll(meals);
	}
	
	/**
	 * This method will update the stock (namingly the amount of meals -- the
	 * rest should be automatically removed due to the fact that there are
	 * usecases))of the warehouse automatically based on the new date d and the
	 * previous date, given in the constructor. It wil also update this previous
	 * date into d, so as to prepare for the next update.
	 * 
	 * @param d
	 *            The new system time.
	 * @throws InvalidHospitalDateException
	 */
	public void updateStock(HospitalDate d) throws InvalidHospitalDateException {
		if (!this.canHaveAsDate(d))
			throw new InvalidHospitalDateException(
					"Invalid HospitalDate given to updateStock() in Warehouse!");
		long timeDiff = this.prevDate.getTimeBetween(d);
	}
	
	public boolean hasPlaster(int plaster){
		return this.unitsOfPlaster >= plaster;
	}
	
	public boolean hasMedication(int plaster){
		return this.unitsOfPlaster >= plaster;
	}
}
