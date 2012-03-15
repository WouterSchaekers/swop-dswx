package schedulerold.task;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents an appointment either still needs to be scheduled or is
 * already scheduled.
 */
public abstract class Task
{
	/**
	 * We will ask the user if they would like to give this task a specific name
	 * or identifier. If they do, then that information gets stored here.
	 * 
	 * Once this task has been scheduled we can then say: "Task with ID "<user
	 * defined ID>" has been scheduled" so the user knows which tasks have been
	 * scheduled after the taskQueue in TaskManager has been updated.
	 */
	private String userDefinedID = "defaultID";
	protected PatientFile patient;

	public Task(PatientFile p) {
		if (p == null) {
			throw new IllegalArgumentException(
					"The given patient may not be null.");
		}
		this.patient = p;
	}

	public PatientFile getPatient() {
		return this.patient;
	}

	/**
	 * This method can be used to clone collections of Schedulables so they are
	 * safe to return.
	 * 
	 * @param myResource
	 *            The collection of Schedulables one would like to clone.
	 * @return A cloned collection of myResource
	 */
	public static LinkedList<Schedulable> cloneCollection(
			Collection<Schedulable> myResource) {
		LinkedList<Schedulable> res = new LinkedList<Schedulable>();
		for (Schedulable sched : myResource)
			res.add(sched);
		return res;
	}

	/**
	 * @return False if the given collection is not a valid resource-source.
	 */
	public static boolean canHaveAsResources(Collection<Schedulable> s) {
		return s != null && !s.isEmpty();
	}

	/**
	 * @return True if d is a valid duration for a Task.
	 */
	public static boolean isValidDuration(long d) {
		return d > 0 && d < (HospitalDate.END_OF_TIME.getTimeSinceStart());
	}

	@Basic
	public String getUserDefinedID() {
		return this.userDefinedID;
	}

	/**
	 * @param id
	 *            If the user wishes to assign an empty String or such, then
	 *            this wish will be granted.
	 */
	@Basic
	public void setUserDefinedID(String id) {
		this.userDefinedID = id;
	}
}
