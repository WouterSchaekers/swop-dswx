package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class Misc extends Medication implements WarehouseItem
{
	public Misc(boolean sensitive, HospitalDate expirationDate) {
		super("Misc", sensitive, expirationDate);
	}

}