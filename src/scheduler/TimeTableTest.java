package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import exceptions.ImpossibleToScheduleException;

@SuppressWarnings("unused")
public class TimeTableTest
{
	@Test
	public void equalsTest() {
		LinkedList<TimeSlot> timeSlots1 = new LinkedList<TimeSlot>();
		LinkedList<TimeSlot> timeSlots2 = new LinkedList<TimeSlot>();
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		timeSlots1.add(t1);
		timeSlots2.add(t2);
		TimeTable table1 = new TimeTable(timeSlots1);
		TimeTable table2 = new TimeTable(timeSlots2);
		assertTrue(table1.equals(table2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void argumentTestFail() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start),
				new TimePoint(new Date(-5), TimeType.stop));
		throw new IllegalStateException(
				"Should not be able to get past all the previous code!!! ");
	}

	@Test
	public void union0Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(8), TimeType.start),
				new TimePoint(new Date(15), TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),
				new TimePoint(new Date(7), TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(7), TimeType.stop));
		TimeSlot t7 = new TimeSlot(new TimePoint(new Date(8), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void union1Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(8), TimeType.start),
				new TimePoint(new Date(15), TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),
				new TimePoint(new Date(9), TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union2Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start),
				new TimePoint(new Date(-2), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(-2), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union3Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start),
				new TimePoint(new Date(-2), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),
				new TimePoint(new Date(15), TimeType.stop));
		TimeSlot t3 = new TimeSlot(
				new TimePoint(new Date(200), TimeType.start), new TimePoint(
						new Date(900), TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(13), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(-5), TimeType.start),
				new TimePoint(new Date(-2), TimeType.stop));
		TimeSlot t6 = new TimeSlot(new TimePoint(new Date(2), TimeType.start),
				new TimePoint(new Date(21), TimeType.stop));
		TimeSlot t7 = new TimeSlot(
				new TimePoint(new Date(200), TimeType.start), new TimePoint(
						new Date(900), TimeType.stop));
		assertTrue(res.equals(new TimeTable(t5, t6, t7)));
	}

	@Test
	public void unionTestWithOnlyOneTable() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start),
				new TimePoint(new Date(15), TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t1, t2);
		TimeTable res = table.getUnion(table2);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}
	
	@Test
	public void unionDoubleRemovalTest() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(6), TimeType.start),
				new TimePoint(new Date(8), TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(6), TimeType.start),
				new TimePoint(new Date(8), TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		assertTrue(table.getUnion(table2).equals(table));
	}

	@Test
	public void eliminateOverlapTest() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1), TimeType.start),
				new TimePoint(new Date(11), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start),
				new TimePoint(new Date(15), TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimePoint[] timePoints = TimeTable.eliminateOverlap(table);
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0].equals(new TimePoint(new Date(1),
				TimeType.start)));
		assertTrue(timePoints[1].equals(new TimePoint(new Date(15),
				TimeType.stop)));
	}

	@Test
	public void eliminateOverlap2Test() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1), TimeType.start),
				new TimePoint(new Date(10), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(10), TimeType.start),
				new TimePoint(new Date(15), TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimePoint[] timePoints = TimeTable.eliminateOverlap(table);
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0].equals(new TimePoint(new Date(1),
				TimeType.start)));
		assertTrue(timePoints[1].equals(new TimePoint(new Date(15),
				TimeType.stop)));
	}

	@Test
	public void invert0Test() throws ImpossibleToScheduleException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1320735601000l),
				TimeType.start), new TimePoint(new Date(1320735602000l),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(1320735603000l),
				TimeType.start), new TimePoint(new Date(1320735604000l),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(Scheduler.START_OF_TIME,
				TimeType.start), new TimePoint(new Date(1320735601000l),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(1320735602000l),
				TimeType.start), new TimePoint(new Date(1320735603000l),
				TimeType.stop));
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(1320735604000l),
				TimeType.start), new TimePoint(Scheduler.END_OF_TIME,
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}

	@Test
	public void invert1Test() throws ImpossibleToScheduleException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new Date(1320735601000l),
				TimeType.start), new TimePoint(new Date(8984651322588l),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new Date(12589765845329l),
				TimeType.start), new TimePoint(new Date(13569856245875l),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(Scheduler.START_OF_TIME,
				TimeType.start), new TimePoint(new Date(1320735601000l),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new Date(8984651322588l),
				TimeType.start), new TimePoint(new Date(12589765845329l),
				TimeType.stop));
		TimeSlot t5 = new TimeSlot(new TimePoint(new Date(13569856245875l),
				TimeType.start), new TimePoint(Scheduler.END_OF_TIME,
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}
}