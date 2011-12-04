package warehouse;

import java.util.Date;

public class SleepingTablets extends Medication
{
	public SleepingTablets(boolean sensitive, Date expirationDate) {
		super("Sleeping Tablets", sensitive, expirationDate);
	}
}