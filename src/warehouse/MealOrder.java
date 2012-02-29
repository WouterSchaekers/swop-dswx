package warehouse;

import scheduler.HospitalDate;
import users.WarehouseAdmin;

public class MealOrder extends StockOrder
{
	private final HospitalDate orderDate;
	private final WarehouseAdmin warehouseAdmin;

	public MealOrder(HospitalDate orderDate, WarehouseAdmin warehouseAdmin) {
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
	public Meal getStockItem() {
		return new Meal(new HospitalDate(orderDate.getTimeSinceStart()
				+ this.TIME_FOR_EXPIRATION));
	}

	@Override
	public String toString() {
		return "Meal";
	}
}