package warehouse;

import java.util.LinkedList;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;
import scheduler.HospitalDate;
import users.WarehouseAdmin;

public class StockProvider
{
	private LinkedList<PlasterOrder> orderedPlaster;
	private LinkedList<MedicationOrder> orderedMedication;
	private LinkedList<MealOrder> orderedMeals;
	private HospitalDate curDate;
	private int extraHour;
	
	
	public StockProvider(){
		this.orderedPlaster = new LinkedList<PlasterOrder>();
		this.orderedMedication = new LinkedList<MedicationOrder>();
		this.orderedMeals = new LinkedList<MealOrder>();
		this.curDate = new HospitalDate();
		this.extraHour = 2;
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
	
	public void updateTime(HospitalDate newDate) throws WarehouseException, WarehouseOverCapacityException{
		this.updatePlaster(newDate);
		this.updateMedication(newDate);
		this.updateMeals(newDate);
		this.curDate = newDate;
	}
	
	private void updatePlaster(HospitalDate hospitalDate) throws WarehouseException{
		for(int i = 0; i < orderedPlaster.size(); i++){
			PlasterOrder curPlasterOrder = orderedPlaster.get(i);
			if(this.mayBeReleased(this.orderedPlaster.get(i), hospitalDate)){
				WarehouseAdmin warehouseAdmin = curPlasterOrder.getWarehouseAdmin();
				warehouseAdmin.addPlasterOrder(curPlasterOrder);
			}
		}
	}
	
	private void updateMedication(HospitalDate hospitalDate) throws WarehouseException{
		for(int i = 0; i < orderedMedication.size(); i++){
			MedicationOrder curMedicationOrder = orderedMedication.get(i);
			if(this.mayBeReleased(this.orderedMedication.get(i), hospitalDate)){
				WarehouseAdmin warehouseAdmin = curMedicationOrder.getWarehouseAdmin();
				warehouseAdmin.addMedicationOrder(curMedicationOrder);
			}
		}
	}
	
	private void updateMeals(HospitalDate hospitalDate) throws WarehouseException, WarehouseOverCapacityException{
		for(int i = 0; i < orderedMeals.size(); i++){
			MealOrder curMealOrder = orderedMeals.get(i);
			if(this.mayBeReleased(this.orderedMeals.get(i), hospitalDate)){
				WarehouseAdmin warehouseAdmin = curMealOrder.getWarehouseAdmin();
				warehouseAdmin.addMealOrder(curMealOrder);
			}
		}
	}
	
	private boolean mayBeReleased(StockOrder stockOrder, HospitalDate hospitalDate){
		HospitalDate orderedDate = stockOrder.getOrderDate();
		if(!hospitalDate.before(new HospitalDate(orderedDate.getYear(), orderedDate.getMonth(), orderedDate.getDay() + this.extraHour, 6, 0, 0))){
			return true;
		}
		return false;
	}
}