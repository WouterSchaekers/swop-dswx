package warehouse;

import scheduler.HospitalDate;

public class MealOrder implements StockOrder
{
	private final HospitalDate orderDate;

	public MealOrder(HospitalDate orderDate) {
		this.orderDate = orderDate;
	}

	public HospitalDate getOrderDate() {
		return new HospitalDate(this.orderDate);
	}
}