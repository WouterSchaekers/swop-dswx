package warehouse;

import java.util.Date;

public class Aspirin extends Medication
{
	public Aspirin(boolean sensitive, Date expirationDate) {
		super("Aspirin", sensitive, expirationDate);
	}
}