package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;

/**
 * Use this observer to notify a TaskManager to update its queue.
 */
public class TimeLordObserverTaskManager implements Observer
{
	private TaskManager taskManager;
	private HospitalDate previousDate;

	/**
	 * Default constructor.
	 * 
	 * @param tm
	 *            The TaskManager this observer should notify, should it get
	 *            notified.
	 */
	public TimeLordObserverTaskManager(TaskManager taskManager,
			HospitalDate initialDate) {
		this.taskManager = taskManager;
		this.previousDate = initialDate;
	}

	@Override
	public void update(Observable o, Object newDate) {
		if (!(newDate instanceof HospitalDate))
			throw new IllegalArgumentException(
					"Object given to TimeLordObserver was not a hospital date!");
		if (this.previousDate.before((HospitalDate) newDate)) {
			this.taskManager.update();
		}
		previousDate = (HospitalDate) newDate;
	}
}