package scheduler.tasks;

import java.util.Collection;
import java.util.Observable;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import system.Hospital;
import system.Location;
import controllers.interfaces.TaskIN;

/**
 * Represents a task that can be scheduled, completed or queued.
 */
public final class Task<T extends TaskDescription> extends Observable implements TaskIN
{
	private TaskState myState_;

	/**
	 * Initialises a new Task and assigns it the queued-state.
	 * <br><br>*** ONLY CREATE IN SCHEDULER-PACKAGE OR CONTROLLERLAYER ***
	 * 
	 * @param description
	 * @param hospital
	 */
	public Task(T description, Hospital hospital) {
		TaskData data = new TaskData(hospital);
		data.setDescription(description);
		myState_ = new QueuedState(data);
	}

	boolean before(Task<?> otherTask) {
		return this.getData().getTimeSlot().getStartPoint().getHospitalDate()
				.before(otherTask.getData().getTimeSlot().getStartPoint().getHospitalDate());
	}

	/**
	 * @return The data for this Task.
	 */
	TaskData getData() {
		return this.myState_.getData();
	}

	
	@SuppressWarnings("unchecked")
	public T getDescription() {
		return (T) myState_.getData().getDescription();
	}

	public boolean isFinished() {
		return myState_.isFinished();
	}

	public boolean isQueued() {
		return myState_.isQueued();
	}

	public boolean isScheduled() {
		return myState_.isScheduled();
	}

	/**
	 * Cycles through to the next state: Queued -> Scheduled -> Finished
	 * 
	 * @param newData
	 *            A data object that contains the data for the new state.
	 */
	void nextState(TaskData newData) {
		this.myState_ = myState_.nextState(newData);
	}

	public Location getLocation() {
		return myState_.getLocation();
	}

	public Collection<Schedulable> getResources() {
		return myState_.getResources();
	}

	public void deInitialise() {
		getDescription().deInit(this);
		
	}

	public void init() {
		getDescription().initTask(this);
	}

	public HospitalDate getDate() {
		return myState_.getData().getStartDate();
	}

}