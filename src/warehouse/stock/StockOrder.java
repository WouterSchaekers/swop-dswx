package warehouse.stock;

import scheduler.HospitalDate;
import exceptions.WarehouseOverCapacityException;
import warehouse.Warehouse;
import warehouse2.item.WarehouseItemType;

public class StockOrder<T extends WarehouseItemType>
{
	private boolean _delivered;
	private Warehouse _warehouse;
	private T _type;

	public StockOrder(Warehouse warehouse, T type) {
		_warehouse = warehouse;
		_type = type;
		_delivered = false;
	}

	public void deliver() throws WarehouseOverCapacityException {
		if(_delivered)
			return;
		_delivered=true;
		_warehouse.add(_type.create());
		
		}
	public boolean isReady(HospitalDate date)
	{
		return true;
	}
}
