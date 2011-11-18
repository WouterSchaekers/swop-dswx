package task;

import java.util.HashMap;
import java.util.Map;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulerException;
import patient.PatientFile;
import scheduler.Appointment;
import scheduler.Scheduler;
import users.Doctor;

public class TaskManager
{
	private Scheduler scheduler;
	private Map<Resource, Task> resourceToTasks;

	public TaskManager(Scheduler scheduler) throws InvalidSchedulerException {
		if (scheduler == null) {
			throw new InvalidSchedulerException(
					"Scheduler is not initialized yet.");
		}
		this.scheduler = scheduler;
		this.resourceToTasks = new HashMap<Resource, Task>();
	}

	public Task getTaskBy(Resource resource) throws InvalidResourceException {
		Task task = resourceToTasks.get(resource);
		if (task == null) {
			throw new InvalidResourceException("Resource does not exist.");
		}
		return task;
	}

	public Task createAppointMent(Doctor doctor, PatientFile file) {
		return null;
	}

	public Appointment scheduldeUltraSound(String focus, boolean recVid,
			boolean recImg) {
		scheduler.toString();
		return null;
	}

	public Appointment scheduldeXRay(String bodypart, int amountOfImages,
			int zoomlevel) {
		return null;
	}

	public Appointment scheduldeBloodAnalysis(String focus, int amountOfAnalyses) {
		return null;
	}

}
