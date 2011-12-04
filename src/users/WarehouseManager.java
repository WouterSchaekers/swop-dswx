package users;

import java.util.Collection;
import warehouse.Depot;
import warehouse.Meal;
import warehouse.Medication;
import exceptions.DepotOverCapacityException;

public class WarehouseManager extends User
{
	private Depot depot;
	public WarehouseManager(Depot depot){
		super("The Warehouse manager");
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

	@Override
	public usertype type() {
		// TODO Auto-generated method stub
		return null;
	}
}