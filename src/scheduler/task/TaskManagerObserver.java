package scheduler.task;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;

public class TaskManagerObserver implements Observer
{
	@Override
	public void update(Observable observable, Object newHospitalDate) {
		if(!(newHospitalDate instanceof HospitalDate)){
			throw new IllegalArgumentException("The object given to the TaskManagerObserver is not a HospitalDate.");
		}
	}
}