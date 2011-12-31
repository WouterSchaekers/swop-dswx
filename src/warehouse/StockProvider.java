package warehouse;

import java.util.LinkedList;
import scheduler.HospitalDate;
import users.HospitalAdmin;
import users.WarehouseAdmin;

public class StockProvider
{
	LinkedList<PlasterOrder> orderedPlaster;
	LinkedList<MedicationOrder> orderedMedication;
	LinkedList<MealOrder> orderedMeals;
	HospitalDate curDate;
	
	public StockProvider(){
		this.orderedPlaster = new LinkedList<PlasterOrder>();
		this.orderedMedication = new LinkedList<MedicationOrder>();
		this.orderedMeals = new LinkedList<MealOrder>();
		this.curDate = new HospitalDate();
	}
	
	public void orderPlaster(WarehouseAdmin warehouseAdmin){
		this.orderedPlaster.add(new PlasterOrder(this.curDate, warehouseAdmin));
	}
	
	public void orderMedication(WarehouseAdmin warehouseAdmin, MedicationType medicationType){
		this.orderedMedication.add(new MedicationOrder(this.curDate, warehouseAdmin, medicationType));
	}
	
	public void orderMeal(WarehouseAdmin warehouseAdmin){
		this.orderedMeals.add(new MealOrder(this.curDate, warehouseAdmin));
	}
}