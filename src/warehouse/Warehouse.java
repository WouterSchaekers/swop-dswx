package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import treatment.Medication;
import exceptions.DepotOverCapacityException;

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
	
	public Warehouse(){
		medication = new ArrayList<Medication>();
		meals = new ArrayList<Meal>();
		initialize();
	}
	
	//TODO: Expiration date
	private void initialize(){
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
	}
	
	public void addPlaster(int units) throws DepotOverCapacityException{
		if(this.unitsOfPlaster + units > MAX_UNITS_OF_PLASTER){
			throw new DepotOverCapacityException("There is too much plaster.");
		}
		this.unitsOfPlaster += units;
	}
	
	public void addMedication(Collection<Medication> medication) throws DepotOverCapacityException{
		if(this.medication.size() + medication.size()> MAX_UNITS_OF_MEDICATION){
			throw new DepotOverCapacityException("There is too much medication.");
		}
		this.medication.addAll(medication);
	}
	
	public void addMeals(Collection<Meal> meals) throws DepotOverCapacityException{
		if(this.meals.size() + meals.size()> MAX_UNITS_OF_MEALS){
			throw new DepotOverCapacityException("There are too many meals.");
		}
		this.meals.addAll(meals);
	}
}
