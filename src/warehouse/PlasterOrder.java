package warehouse;

import scheduler.HospitalDate;
import users.WarehouseAdmin;

public class PlasterOrder extends StockOrder implements StockItem
{
	private final HospitalDate orderDate;
	private final WarehouseAdmin warehouseAdmin;

	public PlasterOrder(HospitalDate orderDate, WarehouseAdmin warehouseAdmin) {
		this.orderDate = orderDate;
		this.warehouseAdmin = warehouseAdmin;
	}

	@Override
	public HospitalDate getOrderDate() {
		return new HospitalDate(this.orderDate);
	}

	@Override
	public WarehouseAdmin getWarehouseAdmin() {
		return this.warehouseAdmin;
	}

	@Override
	public Plaster getStockItem() {
		return new Plaster();
	}

	@Override
	public String toString() {
		return "Plaster";
	}
}