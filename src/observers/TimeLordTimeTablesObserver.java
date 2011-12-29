package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;
import users.UserManager;

public class TimeLordTimeTablesObserver implements Observer
{
	UserManager userManager;
	
	public TimeLordTimeTablesObserver(UserManager userManager){
		this.userManager = userManager;
	}
	@Override
	public void update(Observable arg0, Object newDate) {
		if (!(newDate instanceof HospitalDate))
			throw new IllegalArgumentException(
					"Object given to TimeLordObserver was not a hospital date!");
		this.userManager.updateTimeTables((HospitalDate)newDate);
	}
}