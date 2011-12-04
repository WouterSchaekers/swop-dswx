package scheduler.timetables;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import task.Schedulable;

public class Schedule
{
	private HashMap<Schedulable, TimeTable> timetables;
	private Collection<Schedulable>[] toschedule;
	private int duration;
	private Date now;

	public Schedule(int duration, Collection<Schedulable>... collections) {
		toschedule = collections;
		this.duration = duration;
		this.now = now();
		for (Collection<Schedulable> s : collections)
			for (Schedulable t : s)
				timetables.put(t, new TimeTable(t.getTimeTable()));

	}

	private Date now() {
		// TODO Auto-generated method stub
		return null;
	}

	Comparator<Schedulable> c = new Comparator<Schedulable>()
	{
		TimeTable t1;
		TimeTable t2;

		public int compare(Schedulable o1, Schedulable o2) {
			return 0;
		}
	};

	private boolean schedule(TimeTable used, Collection<Schedulable>... stillToSchedule) {
		for (Schedulable s : stillToSchedule[0]) {
			TimeTable temp = this.timetables.get(s);
			this.timetables.remove(s);
			this.timetables.put(s, used.intersectWith(temp));
		}

		Schedulable[] Ls = sortAvailableness(stillToSchedule[0]);

	}

	private Schedulable[] sortAvailableness(Collection<Schedulable> collection) {
		Schedulable[] returnValue = new Schedulable[collection.size()];
		int i = 0;
		for (Schedulable s : collection)
			returnValue[i++] = s;
		Arrays.sort(returnValue, c);
		// TODO Auto-generated method stub
		return null;
	}
}
