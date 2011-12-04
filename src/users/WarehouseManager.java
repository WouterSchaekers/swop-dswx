package users;

import java.util.Collection;
import warehouse.Depot;
import warehouse.Meal;
import warehouse.Medication;
import exceptions.DepotOverCapacityException;

public class WarehouseManager
{
	private Depot depot;
	public WarehouseManager(Depot depot){
		this.depot = depot;
	}
	
	public void addPlaster(int units) throws DepotOverCapacityException{
		depot.addPlaster(units);
	}
	
	public void addMedication(Collection<Medication> medication) throws DepotOverCapacityException{
		depot.addMedication(medication);
	}
	
	public void addMeals(Collection<Meal> meals) throws DepotOverCapacityException{
		depot.addMeals(meals);
	}
}