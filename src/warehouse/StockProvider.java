package warehouse;

import java.util.LinkedList;
import exceptions.InvalidCategoryNameException;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;
import scheduler.HospitalDate;
import users.WarehouseAdmin;

public class StockProvider
{
	private LinkedList<PlasterOrder> orderedPlaster;
	private LinkedList<MedicationOrder> orderedMedication;
	private LinkedList<MealOrder> orderedMeals;
	private LinkedList<String> stockItems;
	private LinkedList<LinkedList<? extends StockOrder>> orderedItems;
	private HospitalDate curDate;
	private int extraHour;
	
	public StockProvider(){
		this.orderedPlaster = new LinkedList<PlasterOrder>();
		this.orderedMedication = new LinkedList<MedicationOrder>();
		this.orderedMeals = new LinkedList<MealOrder>();
		this.orderedItems = new LinkedList<LinkedList<? extends StockOrder>>();
		this.orderedItems.add(this.orderedPlaster);
		this.orderedItems.add(this.orderedMedication);
		this.orderedItems.add(this.orderedMeals);
		this.stockItems = new LinkedList<String>();
		this.stockItems.add("Plaster");
		this.stockItems.add("Medication");
		this.stockItems.add("Meal");
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
		LinkedList<PlasterOrder> copyOrderedPlaster = new LinkedList<PlasterOrder>();
		for(int i = 0; i < this.orderedPlaster.size(); i++){
			PlasterOrder curPlasterOrder = this.orderedPlaster.get(i);
			if(this.mayBeReleased(this.orderedPlaster.get(i), hospitalDate)){
				WarehouseAdmin warehouseAdmin = curPlasterOrder.getWarehouseAdmin();
				warehouseAdmin.addPlasterOrder(curPlasterOrder);
			}
			else{
				copyOrderedPlaster.add(curPlasterOrder);
			}
		}
		this.orderedPlaster = copyOrderedPlaster;
	}
	
	private void updateMedication(HospitalDate hospitalDate) throws WarehouseException{
		LinkedList<MedicationOrder> copyOrderedMedication = new LinkedList<MedicationOrder>();
		for(int i = 0; i < this.orderedMedication.size(); i++){
			MedicationOrder curMedicationOrder = this.orderedMedication.get(i);
			if(this.mayBeReleased(this.orderedMedication.get(i), hospitalDate)){
				WarehouseAdmin warehouseAdmin = curMedicationOrder.getWarehouseAdmin();
				warehouseAdmin.addMedicationOrder(curMedicationOrder);
			}
			else{
				copyOrderedMedication.add(curMedicationOrder);
			}
		}
		this.orderedMedication = copyOrderedMedication;
	}
	
	private void updateMeals(HospitalDate hospitalDate) throws WarehouseException, WarehouseOverCapacityException{
		LinkedList<MealOrder> copyOrderedMeals = new LinkedList<MealOrder>();
		for(int i = 0; i < this.orderedMeals.size(); i++){
			MealOrder curMealOrder = this.orderedMeals.get(i);
			if(this.mayBeReleased(this.orderedMeals.get(i), hospitalDate)){
				WarehouseAdmin warehouseAdmin = curMealOrder.getWarehouseAdmin();
				warehouseAdmin.addMealOrder(curMealOrder);
			}
			else{
				copyOrderedMeals.add(curMealOrder);
			}
		}
		this.orderedMeals = copyOrderedMeals;
	}
	
	private boolean mayBeReleased(StockOrder stockOrder, HospitalDate hospitalDate){
		HospitalDate orderedDate = stockOrder.getOrderDate();
		if(!hospitalDate.before(new HospitalDate(orderedDate.getYear(), orderedDate.getMonth(), orderedDate.getDay() + this.extraHour, 6, 0, 0))){
			return true;
		}
		return false;
	}
	
	public LinkedList<String> getStockItemNames(){
		return this.stockItems;
	}
	
	public LinkedList<LinkedList<? extends StockOrder>> getOrderedItems(){
		return this.orderedItems;
	}
	
	public LinkedList<? extends StockOrder> getCorrespondingOrderedItems(String itemName) throws InvalidCategoryNameException{
		LinkedList<String> stockItemNames = this.getStockItemNames();
		for(int i = 0; i < stockItemNames.size(); i++){
			if(stockItemNames.get(i).equals(itemName)){
				return this.getOrderedItems().get(i);
			}
		}
		throw new InvalidCategoryNameException("This category does not exist. Please try again.");
	}
}