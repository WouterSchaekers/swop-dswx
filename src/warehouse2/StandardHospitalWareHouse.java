package warehouse2;

import warehouse2.items.Medication;
import warehouse2.items.SleepingTablet;

public class StandardHospitalWareHouse extends Warehouse
{
	public StandardHospitalWareHouse() {
		super();
		intialize();
	}

	private void intialize() {
		this.setMaxCount(SleepingTablet.class, 3);
		this.setMaxCount(Medication.class, 2);

	}
}
