package scheduler;

import java.util.Collection;
import scheduler.requirements.Requirable;
import scheduler.tasks.ScheduledTask;
import system.Campus;
import system.Location;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This interface is implemented by all things that can be scheduled.
 */
public interface Schedulable extends Requirable
{

	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException;

	public TimeTable getTimeTable() throws InvalidTimeSlotException;

	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException;

	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate,
			HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidHospitalDateArgument;

	public void updateTimeTable(HospitalDate newDate);

	public Collection<ScheduledTask> getScheduledTasks();

	public void addScheduledTask(ScheduledTask scheduledTask);
}