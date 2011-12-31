package warehouse;

import java.util.LinkedList;
import users.HospitalAdmin;

public class StockProvider
{
	LinkedList<PlasterOrder> orderedPlaster;
	LinkedList<MedicationOrder> orderedMedication;
	LinkedList<MealOrder> orderedMeals;
	
	public StockProvider(){
		this.orderedPlaster = new LinkedList<PlasterOrder>();
		this.orderedMedication = new LinkedList<MedicationOrder>();
		this.orderedMeals = new LinkedList<MealOrder>();
	}
	
	public void orderPlaster(HospitalAdmin hospitalAdmin){
		
	}
}