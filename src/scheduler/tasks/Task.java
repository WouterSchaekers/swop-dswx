package scheduler.tasks;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import result.Result;
import result.ResultFactory;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import system.Hospital;
import system.Location;
import controllers.interfaces.TaskIN;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

/**
 * Represents a task that can be scheduled, completed or queued.
 */
public final class Task<T extends TaskDescription> extends Observable implements TaskIN, Observer
{
	private TaskState myState_;

	/**
	 * Initialises a new Task and assigns it the queued-state.
	 * 
	 * *** ONLY CREATE IN SCHEDULER-PACKAGE OR CONTROLLERLAYER ***
	 * 
	 * @param description
	 * @param hospital
	 */
	public Task(T description, Hospital hospital) {
		TaskData data = new TaskData(hospital);
		data.setDescription(description);
		myState_ = new QueuedState(data);
		for (Observable o : description.getObservables())
			o.addObserver(this);
	}

	/**
	 * Checks whether the current Task is scheduled before the given Task.
	 * 
	 * @param otherTask
	 *            The Task that has to be checked against.
	 * @return True if the StartTimePoint of this Task is before the
	 *         StartTimePoint of the given Task.
	 */
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

	/**
	 * Returs the Description of this Task.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getDescription() {
		return (T) myState_.getData().getDescription();
	}

	/**
	 * Returns true if the Task is finished.
	 */
	public boolean isFinished() {
		return myState_.isFinished();
	}

	/**
	 * Returns true if the Task is queued.
	 */
	public boolean isQueued() {
		return myState_.isQueued();
	}

	/**
	 * Returns true if the Task is scheduled.
	 */
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

	/**
	 * Returns the Location of this Task.
	 */
	@Override
	public Location getLocation() {
		return myState_.getLocation();
	}

	/**
	 * Returns the resources used for this Task.
	 */
	@Override
	public Collection<Schedulable> getResources() {
		return myState_.getResources();
	}

	/**
	 * Deinitialises the Description.
	 */
	public void deInitialise() {
		getDescription().deInit(this);
	}

	/**
	 * Initialises the Description.
	 */
	public void init() {
		getDescription().initTask(this);
	}

	/**
	 * @return The StartDate of the Task.
	 */
	public HospitalDate getDate() {
		return myState_.getData().getStartDate();
	}

	/**
	 * @return The TimeSlot of the Task.
	 */
	public TimeSlot getTimeSlot() {
		return this.myState_.getData().getTimeSlot().clone();
	}

	/**
	 * A Requirement might be forfilled. Notifies the Observers.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		this.setChanged();
		this.notifyObservers();
		this.notifyObservers(null);
	}

	/**
	 * Returns the 
	 */
	@Override
	public ResultFactory getResultFactory() {
		return getDescription().getResultFactory();
	}

	@Override
	public Result give(ResultFactory builder) throws InvalidResultException, FactoryInstantiationException {
		return getDescription().give(builder);
	}

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}