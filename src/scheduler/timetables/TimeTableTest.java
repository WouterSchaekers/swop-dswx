package scheduler.timetables;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;
import scheduler.timetables.TimePoint.time_type;

public class TimeTableTest
{
	@Test
	public void unionTest() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), time_type.start),
				new TimePoint(new Date(5), time_type.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(8), time_type.start),
				new TimePoint(new Date(15), time_type.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), time_type.start),
				new TimePoint(new Date(7), time_type.end));
		TimeSlot t4 = new TimeSlot(
				new TimePoint(new Date(13), time_type.start), new TimePoint(
						new Date(21), time_type.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(0), time_type.start),
				new TimePoint(new Date(7), time_type.end));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(8), time_type.start),
				new TimePoint(new Date(21), time_type.end));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void union2Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), time_type.start),
				new TimePoint(new Date(5), time_type.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(8), time_type.start),
				new TimePoint(new Date(15), time_type.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), time_type.start),
				new TimePoint(new Date(9), time_type.end));
		TimeSlot t4 = new TimeSlot(
				new TimePoint(new Date(13), time_type.start), new TimePoint(
						new Date(21), time_type.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);

		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(0), time_type.start),
				new TimePoint(new Date(21), time_type.end));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union3Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), time_type.start), new TimePoint(new Date(-2), time_type.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(-2), time_type.start), new TimePoint(new Date(15), time_type.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), time_type.start),new TimePoint(new Date(9), time_type.end));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), time_type.start), new TimePoint(new Date(21), time_type.end));
		TimeSlot t5= new TimeSlot(new TimePoint(new Date(130), time_type.start), new TimePoint(new Date(2100), time_type.end));
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(-2), time_type.start), new TimePoint(new Date(21), time_type.end));
		
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		assertFalse(table.equals(new TimeTable(t5,t6)));
		assertFalse(res.equals(new TimeTable(t5,t6)));
		assertTrue(res.equals(new TimeTable(t1,t6)));

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void union4TestFaal() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-2), time_type.start), new TimePoint(new Date(-5), time_type.end));
		throw new IllegalStateException("Should not be able to get past all the previous code!!! ");
	}
	
	@Test
	public void union5Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), time_type.start), new TimePoint(new Date(-2), time_type.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(2), time_type.start), new TimePoint(new Date(15), time_type.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(200), time_type.start), new TimePoint(new Date(900), time_type.end));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), time_type.start), new TimePoint(new Date(21), time_type.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(-5), time_type.start), new TimePoint(new Date(-2), time_type.end));
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(2), time_type.start), new TimePoint(new Date(21), time_type.end));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(200), time_type.start), new TimePoint(new Date(900), time_type.end));
		assertTrue(res.equals(new TimeTable(t5,t6,t7)));
	}	
	
}
