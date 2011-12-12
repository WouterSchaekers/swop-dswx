package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

@SuppressWarnings("unused")
public class TimeTableTest
{
	HospitalDate h0, h1, h2, h3, h4, h5;
	TimePoint tp00, tp10, tp20, tp30, tp40, tp50, tp01, tp11, tp21, tp31, tp41,
			tp51;

	@Before
	public void initialize() {
		h0 = new HospitalDate(HospitalDate.START_OF_TIME);
		h1 = new HospitalDate(
				HospitalDate.START_OF_TIME.getTotalMillis() + 5000);
		h2 = new HospitalDate(
				HospitalDate.START_OF_TIME.getTotalMillis() + 10000);
		h3 = new HospitalDate(
				HospitalDate.START_OF_TIME.getTotalMillis() + 15000);
		h4 = new HospitalDate(
				HospitalDate.START_OF_TIME.getTotalMillis() + 20000);
		h5 = new HospitalDate(HospitalDate.END_OF_TIME);
		tp00 = new TimePoint(h0, TimeType.start);
		tp10 = new TimePoint(h1, TimeType.start);
		tp20 = new TimePoint(h2, TimeType.start);
		tp30 = new TimePoint(h3, TimeType.start);
		tp40 = new TimePoint(h4, TimeType.start);
		tp50 = new TimePoint(h5, TimeType.start);
		tp01 = new TimePoint(h0, TimeType.stop);
		tp11 = new TimePoint(h1, TimeType.stop);
		tp21 = new TimePoint(h2, TimeType.stop);
		tp31 = new TimePoint(h3, TimeType.stop);
		tp41 = new TimePoint(h4, TimeType.stop);
		tp51 = new TimePoint(h5, TimeType.stop);
	}

	@Test
	public void constructor0Test() throws InvalidTimeSlotException {
		TimeTable t0 = new TimeTable();
	}

	@Test(expected = InvalidTimeSlotException.class)
	public void constructor1Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = null;
		TimeTable t0 = new TimeTable(timeSlots);
	}

	@Test
	public void addTimeSlot0Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		timeSlots.add(ts0);
		TimeTable t0 = new TimeTable(timeSlots);
		t0.addTimeSlot(ts1);
		assertTrue(t0.equals(new TimeTable(ts0, ts1)));
	}

	@Test
	public void addTimeSlot1Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		TimeSlot ts2 = new TimeSlot(tp00, tp21);
		timeSlots.add(ts0);
		TimeTable t0 = new TimeTable(timeSlots);
		t0.addTimeSlot(ts1);
		assertTrue(t0.equals(new TimeTable(ts2)));
	}

	@Test(expected = InvalidSchedulingRequestException.class)
	public void addTimeSlot2Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp31);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		timeSlots.add(ts0);
		TimeTable t0 = new TimeTable(timeSlots);
		t0.addTimeSlot(ts1);
	}

	@Test
	public void hasFreeSlot0Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		timeSlots.add(ts0);
		TimeTable t0 = new TimeTable(timeSlots);
		assertTrue(t0.hasFreeSlotAt(ts1));
	}

	@Test
	public void hasFreeSlot1Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp30, tp41);
		TimeSlot ts2 = new TimeSlot(tp10, tp21);
		timeSlots.add(ts0);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertTrue(t0.hasFreeSlotAt(ts2));
	}

	@Test
	public void hasFreeSlot2Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp20, tp31);
		TimeSlot ts2 = new TimeSlot(tp10, tp21);
		timeSlots.add(ts0);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertTrue(t0.hasFreeSlotAt(ts2));
	}

	@Test
	public void hasFreeSlot3Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		TimeSlot ts2 = new TimeSlot(tp20, tp31);
		timeSlots.add(ts0);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertTrue(t0.hasFreeSlotAt(ts2));
	}

	@Test
	public void hasFreeSlot4Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		TimeSlot ts2 = new TimeSlot(tp20, tp31);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertTrue(t0.hasFreeSlotAt(ts0));
		assertTrue(t0.hasFreeSlotAt(ts2));
	}

	@Test
	public void hasFreeSlot5Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		TimeSlot ts2 = new TimeSlot(tp10, tp31);
		timeSlots.add(ts0);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertFalse(t0.hasFreeSlotAt(ts2));
	}

	@Test
	public void hasFreeSlot6Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp31);
		TimeSlot ts2 = new TimeSlot(tp20, tp41);
		timeSlots.add(ts0);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertFalse(t0.hasFreeSlotAt(ts2));
	}

	@Test
	public void hasFreeSlot7Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp21);
		timeSlots.add(ts0);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertTrue(t0.hasFreeSlotAt(h2, h4));
	}

	@Test
	public void hasFreeSlot8Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		TimeSlot ts0 = new TimeSlot(tp00, tp11);
		TimeSlot ts1 = new TimeSlot(tp10, tp31);
		timeSlots.add(ts0);
		timeSlots.add(ts1);
		TimeTable t0 = new TimeTable(timeSlots);
		assertFalse(t0.hasFreeSlotAt(h2, h4));
	}

	@Test
	public void equalsTest() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots1 = new LinkedList<TimeSlot>();
		LinkedList<TimeSlot> timeSlots2 = new LinkedList<TimeSlot>();
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(tp00, tp11);
		timeSlots1.add(t1);
		timeSlots2.add(t2);
		TimeTable table1 = new TimeTable(timeSlots1);
		TimeTable table2 = new TimeTable(timeSlots2);
		assertTrue(table1.equals(table2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void argumentTestFail() {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(-2),
				TimeType.start), new TimePoint(new HospitalDate(-5),
				TimeType.stop));
		throw new IllegalStateException(
				"Should not be able to get past all the previous code!!! ");
	}

	@Test
	public void union0Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(8),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(7),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(7),
				TimeType.stop));
		TimeSlot t7 = new TimeSlot(new TimePoint(new HospitalDate(8),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void union1Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(8),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(9),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union2Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(-5),
				TimeType.start), new TimePoint(new HospitalDate(-2),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(-2),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new HospitalDate(-5),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union3Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(-5),
				TimeType.start), new TimePoint(new HospitalDate(-2),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(200),
				TimeType.start), new TimePoint(new HospitalDate(900),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(-5),
				TimeType.start), new TimePoint(new HospitalDate(-2),
				TimeType.stop));
		TimeSlot t6 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeSlot t7 = new TimeSlot(new TimePoint(new HospitalDate(200),
				TimeType.start), new TimePoint(new HospitalDate(900),
				TimeType.stop));
		assertTrue(res.equals(new TimeTable(t5, t6, t7)));
	}

	@Test
	public void unionTestWithOnlyOneTable() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(1),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t1, t2);
		TimeTable res = table.getUnion(table2);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}

	@Test
	public void unionDoubleRemovalTest() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(6),
				TimeType.start), new TimePoint(new HospitalDate(8),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(6),
				TimeType.start), new TimePoint(new HospitalDate(8),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		assertTrue(table.getUnion(table2).equals(table));
	}

	@Test
	public void intersect0Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(8),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(7),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t7 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void intersect1Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(8),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(9),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t6 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t7 = new TimeSlot(new TimePoint(new HospitalDate(8),
				TimeType.start), new TimePoint(new HospitalDate(9),
				TimeType.stop));
		TimeSlot t8 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		assertTrue(res.equals(new TimeTable(t6, t7, t8)));
	}

	@Test
	public void intersect2Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(-5),
				TimeType.start), new TimePoint(new HospitalDate(-2),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(-2),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(9),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(130),
				TimeType.start), new TimePoint(new HospitalDate(2100),
				TimeType.stop));
		TimeSlot t6 = new TimeSlot(new TimePoint(new HospitalDate(-2),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeSlot t7 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(9),
				TimeType.stop));
		TimeSlot t8 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		assertFalse(table.equals(new TimeTable(t5, t6)));
		assertFalse(res.equals(new TimeTable(t5, t6)));
		assertTrue(res.equals(new TimeTable(t7, t8)));

	}

	@Test
	public void intersect3Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(-5),
				TimeType.start), new TimePoint(new HospitalDate(-2),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(2),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(200),
				TimeType.start), new TimePoint(new HospitalDate(900),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(21),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(13),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		assertTrue(res.equals(new TimeTable(t5)));
	}

	@Test
	public void intersect4Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(10),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(20),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(25),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(20),
				TimeType.stop));
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(20),
				TimeType.start), new TimePoint(new HospitalDate(30),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2, t3);
		TimeTable table2 = new TimeTable(t4, t5);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(new TimeTable(new TimeSlot(new TimePoint(
				new HospitalDate(10), TimeType.start), new TimePoint(
				new HospitalDate(25), TimeType.stop)))));
	}

	@Test
	public void intersect5Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(10),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(20),
				TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(10),
				TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(20),
				TimeType.stop));
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(20),
				TimeType.start), new TimePoint(new HospitalDate(30),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(new TimeTable(new TimeSlot(new TimePoint(
				new HospitalDate(0), TimeType.start), new TimePoint(
				new HospitalDate(20), TimeType.stop)))));
	}
	
	@Test
	public void intersect6Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(0),
				TimeType.start), new TimePoint(new HospitalDate(10),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(20),
				TimeType.stop));
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(new TimeTable()));
	}
	
	@Test
	public void intersectAll0Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp41);
		TimeSlot t2 = new TimeSlot(tp10, tp21);
		TimeSlot t3 = new TimeSlot(tp20, tp31);
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2, t3);
		Collection<TimeTable> ct = new LinkedList<TimeTable>(Arrays.asList(
				table, table2));
		assertTrue(table2.equals(TimeTable.intersectAll(ct)));
	}
	
	@Test
	public void intersectAll1Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(tp10, tp21);
		TimeSlot t3 = new TimeSlot(tp20, tp31);
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2, t3);
		Collection<TimeTable> ct = new LinkedList<TimeTable>(Arrays.asList(
				table, table2));
		assertTrue(TimeTable.intersectAll(ct).equals(new TimeTable()));
	}
	
	@Test
	public void intersectAll2Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(tp10, tp21);
		TimeSlot t3 = new TimeSlot(tp10, tp31);
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3);
		TimeTable intersection = new TimeTable(t2);
		Collection<TimeTable> ct = new LinkedList<TimeTable>(Arrays.asList(
				table, table2));
		assertTrue(TimeTable.intersectAll(ct).equals(intersection));
	}
	
	@Test
	public void intersectAll3Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp51);
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable();
		TimeTable intersection = new TimeTable();
		Collection<TimeTable> ct = new LinkedList<TimeTable>(Arrays.asList(
				table, table2));
		assertTrue(TimeTable.intersectAll(ct).equals(intersection));
	}
	
	@Test
	public void intersectAll4Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp21);
		TimeSlot t2 = new TimeSlot(tp10, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp21);
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable intersection = new TimeTable(t3);
		Collection<TimeTable> ct = new LinkedList<TimeTable>(Arrays.asList(
				table, table2));
		assertTrue(TimeTable.intersectAll(ct).equals(intersection));
	}
	
	@Test
	public void intersectAll5Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp51);
		TimeSlot t2 = new TimeSlot(tp10, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp41);
		TimeSlot t4 = new TimeSlot(tp00, tp31);
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable table3 = new TimeTable(t3, t4);
		TimeTable intersection = new TimeTable(t2);
		Collection<TimeTable> ct = new LinkedList<TimeTable>(Arrays.asList(
				table, table2, table3));
		assertTrue(TimeTable.intersectAll(ct).equals(intersection));
	}

	@Test
	public void intersectTestWithOnlyOneTable() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(1),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t1, t2);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}

	@Test
	public void intersectManythings() throws InvalidTimeSlotException {
		TimeTable always = new TimeTable(new TimeSlot(new TimePoint(
				new HospitalDate(0), TimeType.start), new TimePoint(
				new HospitalDate(8945890), TimeType.stop)));
		TimeTable slot = new TimeTable(new TimeSlot(new TimePoint(
				new HospitalDate(0), TimeType.start), new TimePoint(
				new HospitalDate(5), TimeType.stop)));
		for (int i = 5; i < 100; i += 2) {
			slot.getUnion(new TimeTable(new TimeSlot(new TimePoint(
					new HospitalDate(i), TimeType.start), new TimePoint(
					new HospitalDate(i + 1), TimeType.stop))));
		}
		assertTrue(slot.equals(slot.getIntersect(always)));
	}

	@Test
	public void intersecAtPoint() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(1),
				TimeType.start), new TimePoint(new HospitalDate(5),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable t = table.getIntersect(table2);
		assertTrue(t.equals(new TimeTable()));
	}

	@Test
	public void eliminateOverlapTest() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(1),
				TimeType.start), new TimePoint(new HospitalDate(11),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		table.eliminateOverlap();
		TimePoint[] timePoints = table.getTimePoints();
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0].equals(new TimePoint(new HospitalDate(1),
				TimeType.start)));
		assertTrue(timePoints[1].equals(new TimePoint(new HospitalDate(15),
				TimeType.stop)));
	}

	@Test
	public void eliminateOverlap1Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(1),
				TimeType.start), new TimePoint(new HospitalDate(10),
				TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(10),
				TimeType.start), new TimePoint(new HospitalDate(15),
				TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		table.eliminateOverlap();
		TimePoint[] timePoints = table.getTimePoints();
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0].equals(new TimePoint(new HospitalDate(1),
				TimeType.start)));
		assertTrue(timePoints[1].equals(new TimePoint(new HospitalDate(15),
				TimeType.stop)));
	}

	@Test
	public void invert0Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(
				1323327601000l), TimeType.start), new TimePoint(
				new HospitalDate(1323327602000l), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(
				1323327603000l), TimeType.start), new TimePoint(
				new HospitalDate(1323327604000l), TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(HospitalDate.START_OF_TIME,
				TimeType.start), new TimePoint(
				new HospitalDate(1323327601000l), TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(
				1323327602000l), TimeType.start), new TimePoint(
				new HospitalDate(1323327603000l), TimeType.stop));
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(
				1323327604000l), TimeType.start), new TimePoint(
				HospitalDate.END_OF_TIME, TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}

	@Test
	public void invert1Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(
				1323327601000l), TimeType.start), new TimePoint(
				new HospitalDate(8984651322588l), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(
				12589765845329l), TimeType.start), new TimePoint(
				new HospitalDate(13569856245875l), TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(HospitalDate.START_OF_TIME,
				TimeType.start), new TimePoint(
				new HospitalDate(1323327601000l), TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(
				8984651322588l), TimeType.start), new TimePoint(
				new HospitalDate(12589765845329l), TimeType.stop));
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(
				13569856245875l), TimeType.start), new TimePoint(
				HospitalDate.END_OF_TIME, TimeType.stop));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}

	@Test
	public void invert2Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new TimePoint(new HospitalDate(
				1323327601000l), TimeType.start), new TimePoint(
				new HospitalDate(8984651322588l), TimeType.stop));
		TimeSlot t2 = new TimeSlot(new TimePoint(new HospitalDate(
				12589765845329l), TimeType.start), new TimePoint(
				new HospitalDate(13569856245875l), TimeType.stop));
		TimeSlot t3 = new TimeSlot(new TimePoint(HospitalDate.START_OF_TIME,
				TimeType.start), new TimePoint(
				new HospitalDate(1323327601000l), TimeType.stop));
		TimeSlot t4 = new TimeSlot(new TimePoint(new HospitalDate(
				8984651322588l), TimeType.start), new TimePoint(
				new HospitalDate(12589765845329l), TimeType.stop));
		TimeSlot t5 = new TimeSlot(new TimePoint(new HospitalDate(
				13569856245875l), TimeType.start), new TimePoint(
				HospitalDate.END_OF_TIME, TimeType.stop));
		TimeTable table = new TimeTable(t2, t1);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}
}