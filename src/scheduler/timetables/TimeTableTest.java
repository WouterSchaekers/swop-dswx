package scheduler.timetables;

import java.sql.Date;
import org.junit.Test;
import scheduler.timetables.TimePoint.time_type;

public class TimeTableTest
{
	@Test
	public void unionTest(){
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), time_type.start), new TimePoint(new Date(5), time_type.start));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(0), time_type.start), new TimePoint(new Date(5), time_type.start));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(0), time_type.start), new TimePoint(new Date(5), time_type.start));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(0), time_type.start), new TimePoint(new Date(5), time_type.start));
		
		TimeTable table = new TimeTable(t1);
	}
}
