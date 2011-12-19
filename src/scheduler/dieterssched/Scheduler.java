package scheduler.dieterssched;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import exceptions.InvalidTimeSlotException;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;

public class Scheduler
{

	public void schedule(List<List<Schedulable>> toschedule, int duration)
			throws InvalidTimeSlotException {

	}

	private void schedule(List<List<Schedulable>> toschedule, int duration,
			TimeTable used) {
		List<Schedulable> nowToSchedule = toschedule.remove(0);
		while (stillPossibleToSchedule(nowToSchedule, used, duration)) {
			sortOnFirstAvailable(nowToSchedule, duration);
		}

	}

	private TimeTable firstAvailableSlot(Schedulable schedulable, int duration) {
		try {
			for (TimeSlot slot : schedulable.getTimeTable().getTimeSlots()) {
				if (slot.getLength() <= duration)
					try {
						return new TimeTable(slot);
					} catch (InvalidTimeSlotException e) {
					}
			}
		} catch (InvalidTimeSlotException e) {
		}
		return null;
	}

	private void sortOnFirstAvailable(List<Schedulable> nowToSchedule,
			final int duration) {
		Collections.sort(nowToSchedule, new Comparator<Schedulable>()
		{

			@Override
			public int compare(Schedulable o1, Schedulable o2) {

				return firstAvailableSlot(o1, duration).getArrayTimeSlots()[0]
						.getStartPoint()
						.compareTo(
								firstAvailableSlot(o2, duration)
										.getArrayTimeSlots()[0].getStartPoint());

			}
		});
	}

	private boolean stillPossibleToSchedule(List<Schedulable> nowToSchedule,
			TimeTable used, int duration) {
		// TODO Auto-generated method stub
		return false;
	}
}
