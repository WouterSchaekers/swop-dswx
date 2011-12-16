package old;

import java.util.*;
import exceptions.*;
import be.kuleuven.cs.som.annotate.*;
import scheduler.task.Schedulable;
import scheduler.*;

/**
 * This class will assign all schedulables their timetables.
 * 
 * <br>
 * <br>
 * The global idea is: <br>
 * <u><b>IN:</b></u><br>
 * A collection of collections. Every collection contains all possible
 * Schedulables of one kind. The highest level collection is a collection of all
 * these collections. <br>
 * If one would like to, for example, schedule 2 nurses, one would have to call
 * the scheduler with, as parameters, 2 collections containing all nurses. <br>
 * <br>
 * <u><b>OUT:</b></u><br>
 * The time of the start of the scheduled appointment.
 * 
 * <br>
 * <br>
 * The scheduler is also responsible for keeping track of the current system
 * time.
 */
public class Scheduler
{
	private static HospitalDate currentSystemTime;

	/**
	 * This method will schedule one of each resources given in the parameters
	 * in the first available and valid timeslot.
	 * 
	 * @param resourcesToSchedule
	 *            Collections of all possible instances of a certain wanted
	 *            resource type. (see class-documentation of Scheduler for a
	 *            better explaination)
	 * @param duration
	 *            The wanted length of the reservation of each resource.
	 * @return The start date of the scheduled appointment.
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 */
	public HospitalDate schedule(long duration,Collection<Schedulable>... resourcesToSchedule)throws QueueException, InvalidDurationException,InvalidSchedulingRequestException,InvalidSchedulingRequestException, InvalidTimeSlotException {
		LinkedList<Collection<Schedulable>> stillToSchedule = new LinkedList<Collection<Schedulable>>();
		LinkedList<Collection<Schedulable>> allTheNeededResources = new LinkedList<Collection<Schedulable>>();

		for (int i = 0; i < resourcesToSchedule.length; i++) {
			stillToSchedule.add(resourcesToSchedule[i]);
			allTheNeededResources.add(resourcesToSchedule[i]);
		}
		
		return schedule(duration, new TimeTable(), stillToSchedule,
				allTheNeededResources);
	}

	/**
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 * @effect schedule(long, collection<schedule>...)
	 */
	public HospitalDate schedule(long duration, Collection<Collection<Schedulable>> resourcesToSchedule) throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		
		LinkedList<Collection<Schedulable>> stillToSchedule = new LinkedList<Collection<Schedulable>>(resourcesToSchedule);
		LinkedList<Collection<Schedulable>> allTheNeededResources = new LinkedList<Collection<Schedulable>>(stillToSchedule);

		if(isValidToScheduleCollection(resourcesToSchedule)) {	
			return schedule(duration, new TimeTable(), stillToSchedule,allTheNeededResources);
		}
		else throw new InvalidSchedulingRequestException("Trying to schedule an invalid amount of resources(more cols of cols than elem in col)");
	}

	/**
	 * This <b><i>PRIVATE</i></b> method will schedule an appointment
	 * recursively for every required resource.
	 * 
	 * @param duration
	 *            The length of the appointment one would like to make.
	 * 
	 * @param used
	 *            Used for recursive purposes. Keeps the current set of all
	 *            intersected timelines stored.
	 * 
	 * @return The date of the scheduled appointment.
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 */
	private HospitalDate schedule(long duration, TimeTable used,LinkedList<Collection<Schedulable>> stillToSchedule,LinkedList<Collection<Schedulable>> allTheNeededResources) throws QueueException, InvalidDurationException,InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		
		if (duration < 0)
			throw new InvalidDurationException(
					"Invalid duration in schedule-method!");

		// First we check if, in this call, the recursion is in it's final step.
		if (stillToSchedule.isEmpty()) {
			// We've calculated the intersected timetable
			// of each of the resources in every collection.
			//
			// Let's schedule them!
			return finalSchedulingStep(duration, used, allTheNeededResources);
		}

		// If this isn't the final step, we need to intersect all resources of
		// the next collection of instances of resources of which the best has
		// to be chosen out of and finally scheduled.
		// For further info: check the documentation on finalSchedulingStep().
		List<Schedulable> resourceQueue = getNextResourceQueue(stillToSchedule);
		Collection<TimeTable> allTheTimeTables = new LinkedList<TimeTable>();

		for (Schedulable s : resourceQueue)
			allTheTimeTables.add(s.getTimeTable());

		TimeTable theIntersection =TimeTable.intersectAll(allTheTimeTables);
		used=used.getUnion(theIntersection);
		return schedule(duration, used, stillToSchedule, allTheNeededResources);
	}

	/**
	 * This method is called as the final step of the recursive private
	 * scheduling method. Using this method in any other location is
	 * <b>absolutely</b> forbidden.
	 * <p>
	 * Let's talk a bit about what the current situation should be, when this
	 * method is called, and what this method does.
	 * </p>
	 * <p>
	 * By now, we have intersected all the time lines of all the requested types
	 * of resources. The result is that we have a timetable for each requested
	 * resourcetype, that is marked as "busy" if all instances of that
	 * resourcetype are busy at that time. In other words: if a slot is free in
	 * that merged timetable, there will be at least one instance of that
	 * resource type that can be scheduled in that slot.
	 * </p>
	 * <p>
	 * The only thing left to do after that is to intersect all intersected
	 * timetables with eachother and get the free slots from that table.
	 * </p>
	 * <p>
	 * Once we have those slots, we can check which one of the resources is
	 * available at that specific time.
	 * </p>
	 * <p>
	 * After we have found those, we need to tell them to schedule themselves at
	 * the found free slot. We can then return the date of the start of that
	 * free slot.
	 * </p>
	 * 
	 * @param used
	 *            The linked list of all TimeTables calculated by the private
	 *            schedule-method.
	 * @param duration
	 *            The duration of the requested appointment.
	 * @return The date at which the appointment has been made.
	 * @throws InvalidSchedulingRequestException
	 *             If used.isEmpty();
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 */
	private HospitalDate finalSchedulingStep(long duration, TimeTable used,LinkedList<Collection<Schedulable>> allTheNeededResources)throws InvalidSchedulingRequestException,InvalidSchedulingRequestException, InvalidTimeSlotException {

		// Get all free timeslots
		TimeTable freeTable = used.getAllFreeSlots(duration);
		// Now we have one timetable that is marked as "busy" if
		// there's at least one instance of each resourcetype
		// that is free at that time.
		//
		// We now need to check which one of those instances it is
		// and then tell it to schedule itself.
		LinkedList<Schedulable> foundResources = new LinkedList<Schedulable>();
		Collection<TimeSlot> freeSlots = freeTable.getTimeSlots();
		for (TimeSlot candidateSlot : freeSlots) {
			for (Collection<Schedulable> candidateCol : allTheNeededResources) {
				for (Schedulable candidate : candidateCol) {
					HospitalDate startDateSlot = candidateSlot.getStartPoint().getDate();
					HospitalDate stopDateSlot = new HospitalDate(candidateSlot.getStartPoint().getDate().getTotalMillis() + duration);
					
					if (candidate.canBeScheduledOn(startDateSlot, stopDateSlot)
							& !foundResources.contains(candidate)) {
						// We found our match in this collection:
						// add it to the list of results and
						// break from the loop.
						foundResources.add(candidate);
						break;
					}
				}
			}
			if (foundResources.size() == allTheNeededResources.size()) {
				//We've found our appointment slot!
				TimePoint startOfAppointment = candidateSlot.getStartPoint();
				TimePoint endOfAppointment = new TimePoint(new HospitalDate(candidateSlot.getStartPoint().getTime() + duration),TimeType.stop);
				TimeSlot foundSlot = new TimeSlot(startOfAppointment, endOfAppointment);
				
				// Tell the found elements to schedule themselves.
				for (Schedulable s : foundResources)
					s.scheduleAt(foundSlot);
				return foundSlot.getStartPoint().getDate();
			}
			foundResources.clear();
		}
		throw new IllegalStateException("Something went wrong... this is embarrasing!");
	}
	
	/**
	 * Will check if the called method doesn't want to e.g. schedule more nurses
	 * than there are available in the hospital.
	 * 
	 * @param toCheck
	 * The collection to check.
	 * @return
	 * True if the given Collection of Collections should be able to be scheduled.
	 */
	private boolean isValidToScheduleCollection(Collection<Collection<Schedulable>> toCheck) {
		HashMap<Collection<Schedulable>, Integer> hm = new HashMap<Collection<Schedulable>,Integer>();
		
		for (Collection<Schedulable> collection : toCheck) {
			if(hm.containsKey(collection))
				if(hm.get(collection) >= collection.size())
					return false;
				else hm.put(collection, hm.get(collection) + 1);
			else if(collection.size() > 0)
				hm.put(collection, 1);
			else return false;
		}
		return true;
	}
	
	/**
	 * This method will update the resource queue so that it contains the
	 * resources from the next collection of stillToScehdule.
	 * 
	 * @effect stillToSchedule.remove();
	 * @throws QueueException
	 *             if the to-schedule-queue is empty.
	 */
	private LinkedList<Schedulable> getNextResourceQueue(
			LinkedList<Collection<Schedulable>> stillToSchedule)
			throws QueueException {
		if (stillToSchedule.isEmpty())
			throw new QueueException(
					"Error while updating resource queue: nothing left to schedule!");
		return new LinkedList<Schedulable>(stillToSchedule.remove(0));
	}

	@Basic
	public static HospitalDate getCurrentSystemTime() {
		if (currentSystemTime == null)
			currentSystemTime = new HospitalDate(0);
		return currentSystemTime;
	}

	@Basic
	public static void setNewSystemTime(HospitalDate newTime) {
		if (!isValidSystemTime(newTime))
			throw new IllegalArgumentException(
					"Invalid new system time given to setNewSystemTime() in Scheduler!");
		currentSystemTime = newTime;
	}

	/**
	 * @return True if t is a valid new system time.
	 */
	private static boolean isValidSystemTime(HospitalDate t) {
		if (currentSystemTime == null)
			return t != null;
		return t != null && t.getTotalMillis() >= currentSystemTime.getTotalMillis();
	}
}