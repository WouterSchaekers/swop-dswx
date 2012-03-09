package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import warehouse.item.MealType;
import warehouse.item.WarehouseItemType;
import warehouse.orderstrat.OrderStrategy;
import warehouse.stock.StockOrder;

public class PatientMealStrategy extends OrderStrategy
{
	private PatientFileManager _man;
	public PatientMealStrategy(MealType type) {
		super(type);
		
	}

	@Override
	public Collection<StockOrder<? extends WarehouseItemType>> getOrders(
			Warehouse _warehouse) {
		int orderCount=		_warehouse.getCurrentCount(this._type) - countNeeded();
		Collection<StockOrder<?extends WarehouseItemType>> orders = new ArrayList<StockOrder<? extends WarehouseItemType>>();
		for(int i = 0 ; i < orderCount;i++)
			orders.add(new StockOrder<WarehouseItemType>(_warehouse, _type, new HospitalDate()));
		return orders;
	}

	private int countNeeded() {
		int patients = _man.amountOfActivePatients();
		return patients*4;//ofzo
	}

}
