package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;
import scheduler.task.TaskManager;

/**
 * Use this observer to notify a TaskManager to update its queue.
 */
public class TimeLordObserverTaskManager implements Observer
{
	private TaskManager myTM;
	
	/**
	 * Default constructor.
	 * 
	 * @param tm
	 *            The TaskManager this observer should notify, should it get
	 *            notified.
	 */
	public TimeLordObserverTaskManager(TaskManager tm) {
		this.myTM = tm;
	}
	
	@Override
	public void update(Observable o, Object newDate) {
		if (!(newDate instanceof HospitalDate))
			throw new IllegalArgumentException(
					"Object given to TimeLordObserver was not a hospital date!");
		myTM.update();
	}

}
