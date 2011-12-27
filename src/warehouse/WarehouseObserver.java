package warehouse;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;

public class WarehouseObserver implements Observer
{
	@Override
	public void update(Observable observable, Object newHospitalDate) {
		if(!(newHospitalDate instanceof HospitalDate)){
			throw new IllegalArgumentException("The object given to the WarehouseObserver is not a HospitalDate.");
		}
	}
}