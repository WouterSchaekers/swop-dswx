package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class SleepingTablets extends Medication
{
	public SleepingTablets(boolean sensitive, HospitalDate expirationDate) {
		super("Sleeping Tablets", sensitive, expirationDate,
				new SleepingTabletsType());
	}

}