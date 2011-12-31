package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class Misc extends Medication
{
	public Misc(boolean sensitive, HospitalDate expirationDate) {
		super("Misc", sensitive, expirationDate, new MiscType());
	}
}