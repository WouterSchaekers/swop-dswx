package scheduler.timetables;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.junit.Test;
import scheduler.TimeType;

public class TimeTableTest
{
	@Test
	public void equalsTest(){
		Collection<TimeSlot> timeSlots1 = new ArrayList<TimeSlot>();
		Collection<TimeSlot> timeSlots2 = new ArrayList<TimeSlot>();
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.end));
		timeSlots1.add(t1);
		timeSlots2.add(t2);
		TimeTable table1 = new TimeTable(timeSlots1);
		TimeTable table2 = new TimeTable(timeSlots2);
		assertTrue(table1.equals(table2));
	}
	
	@Test
	public void unionTest() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(8), TimeType.start),
				new TimePoint(new Date(15), TimeType.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),
				new TimePoint(new Date(7), TimeType.end));
		TimeSlot t4 = new TimeSlot(
				new TimePoint(new Date(13), TimeType.start), new TimePoint(
						new Date(21), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(0), TimeType.start), new TimePoint(new Date(7), TimeType.end));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(8), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void union2Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(8), TimeType.start),
				new TimePoint(new Date(15), TimeType.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),
				new TimePoint(new Date(9), TimeType.end));
		TimeSlot t4 = new TimeSlot(
				new TimePoint(new Date(13), TimeType.start), new TimePoint(
						new Date(21), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(21), TimeType.end));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union3Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start), new TimePoint(new Date(-2), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),new TimePoint(new Date(9), TimeType.end));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		TimeSlot t5= new TimeSlot(new TimePoint(new Date(130), TimeType.start), new TimePoint(new Date(2100), TimeType.end));
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		assertFalse(table.equals(new TimeTable(t5,t6)));
		assertFalse(res.equals(new TimeTable(t5,t6)));
		assertTrue(res.equals(new TimeTable(t1,t6)));

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void union4TestFaal() {
		@SuppressWarnings("unused")
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start), new TimePoint(new Date(-5), TimeType.end));
		throw new IllegalStateException("Should not be able to get past all the previous code!!! ");
	}
	
	@Test
	public void union5Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start), new TimePoint(new Date(-2), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(2), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(200), TimeType.start), new TimePoint(new Date(900), TimeType.end));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start), new TimePoint(new Date(-2), TimeType.end));
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(2), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(200), TimeType.start), new TimePoint(new Date(900), TimeType.end));
		assertTrue(res.equals(new TimeTable(t5,t6,t7)));
	}
	
	@Test
	public void unionTestWithOnlyOneTable() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1), TimeType.start), new TimePoint(new Date(5), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t1, t2);
		TimeTable res = table.getUnion(table);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}

	@Test
	public void intersect1Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start), new TimePoint(new Date(-2), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(2), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(200), TimeType.start), new TimePoint(new Date(900), TimeType.end));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		assertTrue(res.equals(new TimeTable(t5)));
	}
}
