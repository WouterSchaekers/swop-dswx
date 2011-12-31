package observers;

import java.util.Observable;
import java.util.Observer;
import patient.Diagnose;
import scheduler.HospitalDate;
import scheduler.task.TaskManager;

public class DiagnoseObserverTaskManager implements Observer
{
	private TaskManager taskmanager;
	/**
	 * 
	 * @param d
	 * @param taskmanager
	 */
	public DiagnoseObserverTaskManager( TaskManager taskmanager) {
		this.taskmanager = taskmanager;
	}
	/**
	 * 
	 */
	@Override
	public void update(Observable arg0, Object diag) {
			this.taskmanager.update();

	}

}