package warehouse.stock;

import scheduler.HospitalDate;
import exceptions.WarehouseOverCapacityException;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;

public class StockOrder<T extends WarehouseItemType>
{
	private boolean _delivered;
	private Warehouse _warehouse;
	private T _type;
	private HospitalDate _creationDate;

	public StockOrder(Warehouse warehouse, T type,HospitalDate creationDate) {
		_warehouse = warehouse;
		_type = type;
		_delivered = false;
		_creationDate	=creationDate;
	}

	public void deliver() throws WarehouseOverCapacityException {
		if(_delivered)
			return;
		_delivered=true;
		_warehouse.add(_type.create(_type.getExpirationDate(_creationDate)));
		
		}

	public boolean isReady(HospitalDate date)
	{
		return true;
	}
	
}
