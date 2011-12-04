package scheduler.timetables;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import task.Schedulable;

public class Schedule
{

//		public ScheduleableTimeTable(Schedulable s,TimeTable t)
//		{
//			this.s=s;
//			this.t=t;
//		}
//		public Schedulable getS() {
//			return s;
//		}
//		public void setS(Schedulable s) {
//			this.s = s;
//		}
//		public TimeTable getT() {
//			return t;
//		}
//		public void setT(TimeTable t) {
//			this.t = t;
//		}
//		private Schedulable s;
//		private TimeTable t;
//	}
//	
		
	private HashMap<Schedulable,TimeTable> timetables;
	private Collection<Schedulable>[] toschedule;
	private int duration;
	private Date now;
	public Schedule(int duration,Collection<Schedulable>... collections ){
		toschedule=collections;
		this.duration=duration;
		this.now=now();
		for(Collection<Schedulable> s :collections)
			for(Schedulable t: s)
				timetables.put( t,new TimeTable(t.getTimeTable()));
		
	}
	private Date now() {
		// TODO Auto-generated method stub
		return null;
	}
	Comparator<Schedulable> c = new Comparator<Schedulable>()
	{
		TimeTable t1;
		TimeTable t2;
		@Override
		public int compare(Schedulable o1, Schedulable o2) {
			// TODO Auto-generated method stub
			return 0;
		}
	};
	private boolean schedule(TimeTable used,Collection<Schedulable>...stillToSchedule){
		for(Schedulable s: stillToSchedule[0]){
			TimeTable temp = this.timetables.get(s);
			this.timetables.remove(s);
			this.timetables.put(s,used.intersectWith(temp));
			}
		
	Schedulable[] Ls = sortAvailableNess(stillToSchedule[0]);
	
	}
	private Schedulable[] sortAvailableNess(Collection<Schedulable> collection) {
		Schedulable[] returnValue = new Schedulable[collection.size()];
		int i = 0 ;
		for(Schedulable s : collection)
			returnValue[i++]=s;
		Arrays.sort(returnValue, c);
		// TODO Auto-generated method stub
		return null;
	}
	}
