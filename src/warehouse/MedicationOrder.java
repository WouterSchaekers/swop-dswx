package warehouse;

import scheduler.HospitalDate;

public class MedicationOrder implements StockOrder
{
	private final HospitalDate orderDate;

	public MedicationOrder(HospitalDate orderDate) {
		this.orderDate = orderDate;
	}

	public HospitalDate getOrderDate() {
		return new HospitalDate(this.orderDate);
	}
}