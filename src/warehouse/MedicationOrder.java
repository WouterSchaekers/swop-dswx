package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;
import users.WarehouseAdmin;

public class MedicationOrder extends StockOrder
{
	private final HospitalDate orderDate;
	private final MedicationType medicationType;
	private final WarehouseAdmin warehouseAdmin;

	public MedicationOrder(HospitalDate orderDate,
			WarehouseAdmin warehouseAdmin, MedicationType medicationType) {
		this.orderDate = orderDate;
		this.warehouseAdmin = warehouseAdmin;
		this.medicationType = medicationType;
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
	public Medication getStockItem() {
		if (medicationType.equals(new ActivatedCarbon(false, null)))
			return new ActivatedCarbon(false, new HospitalDate(
					this.orderDate.getTimeSinceStart()
							+ this.TIME_FOR_EXPIRATION));
		else if (medicationType.equals(new Aspirin(false, null)))
			return new Aspirin(false, new HospitalDate(
					this.orderDate.getTimeSinceStart()
							+ this.TIME_FOR_EXPIRATION));
		else if (medicationType.equals(new Misc(false, null)))
			return new Misc(false, new HospitalDate(
					this.orderDate.getTimeSinceStart()
							+ this.TIME_FOR_EXPIRATION));
		else if (medicationType.equals(new SleepingTablets(false, null)))
			return new SleepingTablets(false, new HospitalDate(
					this.orderDate.getTimeSinceStart()
							+ this.TIME_FOR_EXPIRATION));
		else
			return new Vitamins(false, new HospitalDate(
					this.orderDate.getTimeSinceStart()
							+ this.TIME_FOR_EXPIRATION));
	}
}