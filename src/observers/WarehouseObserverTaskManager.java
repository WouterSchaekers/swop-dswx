package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler.task.TaskManager;

public class WarehouseObserverTaskManager implements Observer
{
	TaskManager taskManager;

	public WarehouseObserverTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	@Override
	public void update(Observable o, Object arg) {
		taskManager.update();
	}
}