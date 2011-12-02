package depot;

import java.util.Collection;
import exceptions.DepotOverCapacityException;

public class DepotManager
{
	private Depot depot;
	public DepotManager(Depot depot){
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