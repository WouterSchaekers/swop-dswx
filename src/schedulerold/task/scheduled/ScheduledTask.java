package schedulerold.task.scheduled;
//package scheduler.task.scheduled;
//
//import java.util.Collection;
//import java.util.LinkedList;
//import patient.PatientFile;
//import scheduler.HospitalDate;
//import scheduler.TimeSlot;
//import scheduler.task.Schedulable;
//import scheduler.task.Task;
//import system.Whereabouts;
//import be.kuleuven.cs.som.annotate.Basic;
//import exceptions.InvalidResourceException;
//
///**
// * This class represents a Task that has been scheduled. It is to be stored in
// * the according TimeTables so that the Schedulables know what Task they need to
// * perform at a certain given TimeSlot.
// */
//public class ScheduledTask extends Task
//{
//	private LinkedList<Schedulable> _resources;
//	
//	private TimeSlot _timeSlot;
//
//	/**
//	 * Default constructor.
//	 * 
//	 * @param resources
//	 *            The resources that are being used by this Task.
//	 * @param timeSlot
//	 *            The TimeSlot during which this Task has been scheduled at.
//	 * @throws InvalidResourceException
//	 */
//	public ScheduledTask(PatientFile p, Collection<Schedulable> resources,
//			TimeSlot timeSlot) throws InvalidResourceException {
//		super(p);
//		if (!super.canHaveAsResources(resources)) {
//			throw new InvalidResourceException(
//					"Invalid resource passed to Task constructor!");
//		}
//		this._resources = Task.cloneCollection(resources);
//		this.addTaskToResources();
//		this._timeSlot = timeSlot;
//	}
//
//	@Basic
//	public TimeSlot getTimeSlot() {
//		return _timeSlot.clone();
//	}
//
//	@Basic
//	public LinkedList<Schedulable> getResources() {
//		return super.cloneCollection(_resources);
//	}
//
//	private void addTaskToResources() {
//		for (int i = 0; i < this._resources.size(); i++) {
//			this._resources.get(i).addScheduledTask(this);
//		}
//	}
//
//	/**
//	 * @return The length of the duration of this ScheduledTask.
//	 */
//	public long getDuration() {
//		return _timeSlot.getStopPoint().getTime()
//				- _timeSlot.getStartPoint().getTime();
//	}
//
//	/**
//	 * @return The start date of this Task.
//	 */
//	public HospitalDate getStartDate() {
//		return new HospitalDate(this._timeSlot.getStartPoint().getHospitalDate());
//	}
//
//	public String toString() {
//		StringBuilder asdf = new StringBuilder();
//		for (Schedulable s : _resources) {
//			asdf.append(s);
//			asdf.append('\n');
//		}
//		return _timeSlot.toString();
//	}
//}
