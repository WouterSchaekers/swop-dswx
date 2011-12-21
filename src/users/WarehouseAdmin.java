package users;

import java.util.Collection;
import treatment.Medication;
import warehouse.Warehouse;
import warehouse.Meal;
import exceptions.DepotOverCapacityException;
import exceptions.InvalidNameException;

public class WarehouseAdmin extends User
{
	private Warehouse depot;
	public WarehouseAdmin(Warehouse depot) throws InvalidNameException{
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

}