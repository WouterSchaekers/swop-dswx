package warehouse;

import java.util.Date;

public class Vitamins extends Medication
{
	public Vitamins(boolean sensitive, Date expirationDate) {
		super("Vitamins", sensitive, expirationDate);
	}
}