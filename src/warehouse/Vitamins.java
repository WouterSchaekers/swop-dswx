package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class Vitamins extends Medication implements WarehouseItem
{
	public Vitamins(boolean sensitive, HospitalDate expirationDate) {
		super("Vitamins", sensitive, expirationDate);
	}
}