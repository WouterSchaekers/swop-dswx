package users;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;
import scheduler.task.scheduled.ScheduledTask;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public abstract class SchedulableUser extends User implements Schedulable
{
	protected TimeTable timeTable = new TimeTable();
	protected Collection<ScheduledTask> scheduledTasks;

	protected SchedulableUser(String name) throws InvalidNameException {
		super(name);
		this.timeTable = new TimeTable();
		this.scheduledTasks = new LinkedList<ScheduledTask>();
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		;
		return timeTable.hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException {
		this.timeTable.addTimeSlot(timeSlot);
	}

	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return new TimeTable(this.timeTable.getTimeSlots());
	}

	public void updateTimeTable(HospitalDate newDate) {
		this.timeTable.updateTimeTable(newDate);
	}

	public Collection<ScheduledTask> getScheduledTasks() {
		return this.scheduledTasks;
	}

	public void addScheduledTask(ScheduledTask scheduledTask) {
		this.scheduledTasks.add(scheduledTask);
	}
}