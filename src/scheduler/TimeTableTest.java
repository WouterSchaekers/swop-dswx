package scheduler;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import org.junit.Test;

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
	
	@Test(expected = IllegalArgumentException.class)
	public void argumentTestFail() {
		@SuppressWarnings("unused")
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start), new TimePoint(new Date(-5), TimeType.end));
		throw new IllegalStateException("Should not be able to get past all the previous code!!! ");
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
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(130), TimeType.start), new TimePoint(new Date(2100), TimeType.end));
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		assertFalse(table.equals(new TimeTable(t5,t6)));
		assertFalse(res.equals(new TimeTable(t5,t6)));
		assertTrue(res.equals(new TimeTable(t1,t6)));

	}
	
	@Test
	public void union4Test() {
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
		TimeTable res = table.getUnion(table2);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}

	@Test
	public void intersectTest() {
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
		TimeTable res = table.getIntersect(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(2), TimeType.start), new TimePoint(new Date(5), TimeType.end));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void intersect2Test() {
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
		TimeTable res = table.getIntersect(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(2), TimeType.start), new TimePoint(new Date(5), TimeType.end));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(8), TimeType.start), new TimePoint(new Date(9), TimeType.end));
		TimeSlot t8 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		assertTrue(res.equals(new TimeTable(t6,t7,t8)));
	}

	@Test
	public void intersect3Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start), new TimePoint(new Date(-2), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),new TimePoint(new Date(9), TimeType.end));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(130), TimeType.start), new TimePoint(new Date(2100), TimeType.end));
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start), new TimePoint(new Date(21), TimeType.end));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(2), TimeType.start), new TimePoint(new Date(9), TimeType.end));
		TimeSlot t8 = new TimeSlot(new TimePoint(new Date(13), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		assertFalse(table.equals(new TimeTable(t5,t6)));
		assertFalse(res.equals(new TimeTable(t5,t6)));
		assertTrue(res.equals(new TimeTable(t7,t8)));

	}
	
	@Test
	public void intersect4Test() {
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
	
	@Test
	public void intersect5Test()
	{
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(50), TimeType.start), new TimePoint(new Date(5000), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(60), TimeType.end));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(100), TimeType.start), new TimePoint(new Date(150), TimeType.end));
		TimeSlot tr2 = new TimeSlot(new TimePoint(new Date(50), TimeType.start), new TimePoint(new Date(60), TimeType.end));
		TimeSlot tr3 = new TimeSlot(new TimePoint(new Date(100), TimeType.start), new TimePoint(new Date(150), TimeType.end));
		TimeTable result = new TimeTable(tr2,tr3);
		TimeTable filter = new TimeTable(t1);
		TimeTable elements = new TimeTable(t2,t3);
		Collection<TimeTable> ct = new ArrayList<TimeTable>(Arrays.asList(filter,elements));
		assertTrue(result.equals(elements.intersectAll(ct)));
	}
	
	@Test
	public void intersect6Test()
	{
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start), new TimePoint(new Date(10), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(20), TimeType.end));
//		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(25), TimeType.end));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(20), TimeType.end));
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(20), TimeType.start), new TimePoint(new Date(30), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t4, t5);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(new TimeTable(new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(20), TimeType.end)))));
	}
	
	@Test
	public void intersectTestWithOnlyOneTable() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1), TimeType.start), new TimePoint(new Date(5), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t1, t2);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}
	
	@Test
	public void eliminateOverlapTest() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1), TimeType.start), new TimePoint(new Date(11), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimePoint[] timePoints = TimeTable.eliminateOverlap(table);
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0].equals(new TimePoint(new Date(1), TimeType.start)));
		assertTrue(timePoints[1].equals(new TimePoint(new Date(15), TimeType.end)));
	}
	
	@Test
	public void eliminateOverlap2Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1), TimeType.start), new TimePoint(new Date(10), TimeType.end));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start), new TimePoint(new Date(15), TimeType.end));
		TimeTable table = new TimeTable(t1, t2);
		TimePoint[] timePoints = TimeTable.eliminateOverlap(table);
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0].equals(new TimePoint(new Date(1), TimeType.start)));
		assertTrue(timePoints[1].equals(new TimePoint(new Date(15), TimeType.end)));
	}
}
