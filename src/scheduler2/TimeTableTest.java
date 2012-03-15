package scheduler2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class TimeTableTest
{
	HospitalDate h0, h1, h2, h3, h4, h5;
	TimePoint tp00, tp10, tp20, tp30, tp40, tp50, tp01, tp11, tp21, tp31, tp41,
			tp51;

	@Before
	public void initialize() {
		h0 = new HospitalDate(HospitalDate.START_OF_TIME);
		h1 = new HospitalDate(5000);
		h2 = new HospitalDate(10000);
		h3 = new HospitalDate(15000);
		h4 = new HospitalDate(20000);

		h5 = new HospitalDate(HospitalDate.END_OF_TIME);
		tp00 = new StartTimePoint(h0);
		tp10 = new StartTimePoint(h1);
		tp20 = new StartTimePoint(h2);
		tp30 = new StartTimePoint(h3);
		tp40 = new StartTimePoint(h4);
		tp50 = new StartTimePoint(h5);
		tp01 = new StopTimePoint(h0);
		tp11 = new StopTimePoint(h1);
		tp21 = new StopTimePoint(h2);
		tp31 = new StopTimePoint(h3);
		tp41 = new StopTimePoint(h4);
		tp51 = new StopTimePoint(h5);
	}

	@Test
	public void constructor0Test() throws InvalidTimeSlotException {
		new TimeTable();
	}

	@Test(expected = InvalidTimeSlotException.class)
	public void constructor1Test() throws InvalidTimeSlotException {
		LinkedList<TimeSlot> timeSlots = null;
		new TimeTable(timeSlots);
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
		new TimeSlot(new StartTimePoint(new HospitalDate(-2)),
				new StopTimePoint(new HospitalDate(-5)));
		throw new IllegalStateException(
				"Should not be able to get past all the previous code!!! ");
	}

	@Test
	public void union0Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(8)),
				new StopTimePoint(new HospitalDate(15)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(2)),
				new StopTimePoint(new HospitalDate(7)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(21)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(7)));
		TimeSlot t7 = new TimeSlot(new StartTimePoint(new HospitalDate(8)),
				new StopTimePoint(new HospitalDate(21)));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void union1Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(8)),
				new StopTimePoint(new HospitalDate(15)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(2)),
				new StopTimePoint(new HospitalDate(9)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(21)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(21)));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union2Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(2)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(1)

		), new StopTimePoint(new HospitalDate(21)));
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable res = table.getUnion(table2);
		TimeSlot t6 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(21)));
		assertTrue(res.equals(new TimeTable(t6)));
	}

	@Test
	public void union3Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(4)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(6)),
				new StopTimePoint(new HospitalDate(15)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(200)),
				new StopTimePoint(new HospitalDate(900)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(21)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getUnion(table2);
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(4)));
		TimeSlot t6 = new TimeSlot(new StartTimePoint(new HospitalDate(6)),
				new StopTimePoint(new HospitalDate(21)));
		TimeSlot t7 = new TimeSlot(new StartTimePoint(new HospitalDate(200)),
				new StopTimePoint(new HospitalDate(900)));
		assertTrue(res.equals(new TimeTable(t5, t6, t7)));
	}

	@Test
	public void unionTestWithOnlyOneTable() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(15)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t1, t2);
		TimeTable res = table.getUnion(table2);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}

	@Test
	public void unionDoubleRemovalTest() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(6)),
				new StopTimePoint(new HospitalDate(8)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(6)),
				new StopTimePoint(new HospitalDate(8)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		assertTrue(table.getUnion(table2).equals(table));
	}

	@Test
	public void intersect0Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(8)),
				new StopTimePoint(new HospitalDate(15)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(2)),
				new StopTimePoint(new HospitalDate(7)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(21)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t6 = new TimeSlot(new StartTimePoint(new HospitalDate(2)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t7 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(15)));
		assertTrue(res.equals(new TimeTable(t6, t7)));
	}

	@Test
	public void intersect1Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(8)),
				new StopTimePoint(new HospitalDate(15)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(2)),
				new StopTimePoint(new HospitalDate(9)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(21)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t6 = new TimeSlot(new StartTimePoint(new HospitalDate(2)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t7 = new TimeSlot(new StartTimePoint(new HospitalDate(8)),
				new StopTimePoint(new HospitalDate(9)));
		TimeSlot t8 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(15)));
		assertTrue(res.equals(new TimeTable(t6, t7, t8)));
	}

	@Test
	public void intersect2Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(tp10, tp51);

		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(130)),
				new StopTimePoint(new HospitalDate(2100)));
		TimeSlot t6 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(21)));
		assertFalse(table.equals(new TimeTable(t5, t6)));
		assertFalse(res.equals(new TimeTable(t5, t6)));
		assertTrue(res.equals(new TimeTable()));

	}

	@Test
	public void intersect3Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(4)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(2)),
				new StopTimePoint(new HospitalDate(15)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(200)),
				new StopTimePoint(new HospitalDate(900)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(21)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4);
		TimeTable res = table.getIntersect(table2);
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(13)),
				new StopTimePoint(new HospitalDate(15)));
		assertTrue(res.equals(new TimeTable(t5)));
	}

	@Test
	public void intersect4Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(10)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(20)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(25)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(20)));
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(20)),
				new StopTimePoint(new HospitalDate(30)));
		TimeTable table = new TimeTable(t1, t2, t3);
		TimeTable table2 = new TimeTable(t4, t5);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res
				.equals(new TimeTable(new TimeSlot(new StartTimePoint(
						new HospitalDate(10)), new StopTimePoint(
						new HospitalDate(25))))));
	}

	@Test
	public void intersect5Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(10)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(20)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(10)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(20)));
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(20)),
				new StopTimePoint(new HospitalDate(30)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res
				.equals(new TimeTable(new TimeSlot(new StartTimePoint(
						new HospitalDate(0)), new StopTimePoint(
						new HospitalDate(20))))));
	}

	@Test
	public void intersect6Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new StopTimePoint(new HospitalDate(10)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(20)));
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(new TimeTable()));
	}

	@Test
	public void intersectAll0Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
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
	public void intersectAll1Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
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
	public void intersectAll2Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
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
	public void intersectAll3Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp51);
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable();
		TimeTable intersection = new TimeTable();
		Collection<TimeTable> ct = new LinkedList<TimeTable>(Arrays.asList(
				table, table2));
		assertTrue(TimeTable.intersectAll(ct).equals(intersection));
	}

	@Test
	public void intersectAll4Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
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
	public void intersectAll5Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
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
	public void intersectTestWithOnlyOneTable()
			throws InvalidTimeSlotException, InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(15)));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t1, t2);
		TimeTable res = table.getIntersect(table2);
		assertTrue(res.equals(table2));
		assertTrue(res.equals(table));
	}

	@Test
	public void intersectManythings() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeTable always = new TimeTable(new TimeSlot(new StartTimePoint(
				new HospitalDate(0)), new StopTimePoint(new HospitalDate(
				8945890))));
		TimeTable slot = new TimeTable(new TimeSlot(new StartTimePoint(
				new HospitalDate(0)), new StopTimePoint(new HospitalDate(5))));
		for (int i = 5; i < 100; i += 2) {
			slot.getUnion(new TimeTable(new TimeSlot(new StartTimePoint(
					new HospitalDate(i)), new StopTimePoint(new HospitalDate(
					i + 1)))));
		}
		assertTrue(slot.equals(slot.getIntersect(always)));
	}

	@Test
	public void intersecAtPoint() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(5)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(15)));
		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		TimeTable t = table.getIntersect(table2);
		assertTrue(t.equals(new TimeTable()));
	}

	@Test
	public void eliminateOverlapTest() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(11)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(15)));
		TimeTable table = new TimeTable(t1, t2);
		table.eliminateOverlap();
		TimePoint[] timePoints = table.getTimePoints();
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0]
				.equals(new StartTimePoint(new HospitalDate(1))));
		assertTrue(timePoints[1]
				.equals(new StopTimePoint(new HospitalDate(15))));
	}

	@Test
	public void eliminateOverlap1Test() throws InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new StopTimePoint(new HospitalDate(10)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(10)),
				new StopTimePoint(new HospitalDate(15)));
		TimeTable table = new TimeTable(t1, t2);
		table.eliminateOverlap();
		TimePoint[] timePoints = table.getTimePoints();
		assertTrue(timePoints.length == 2);
		assertTrue(timePoints[0]
				.equals(new StartTimePoint(new HospitalDate(1))));
		assertTrue(timePoints[1]
				.equals(new StopTimePoint(new HospitalDate(15))));
	}

	@Test
	public void invert0Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(
				1323327601000l)), new StopTimePoint(new HospitalDate(
				1323327602000l)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(
				1323327603000l)), new StopTimePoint(new HospitalDate(
				1323327604000l)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(
				HospitalDate.START_OF_TIME), new StopTimePoint(
				new HospitalDate(1323327601000l)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(
				1323327602000l)), new StopTimePoint(new HospitalDate(
				1323327603000l)));
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(
				1323327604000l)), new StopTimePoint(HospitalDate.END_OF_TIME));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}

	@Test
	public void invert1Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(
				1323327601000l)), new StopTimePoint(new HospitalDate(
				8984651322588l)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(
				12589765845329l)), new StopTimePoint(new HospitalDate(
				13569856245875l)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(
				HospitalDate.START_OF_TIME), new StopTimePoint(
				new HospitalDate(1323327601000l)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(
				8984651322588l)), new StopTimePoint(new HospitalDate(
				12589765845329l)));
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(
				13569856245875l)), new StopTimePoint(HospitalDate.END_OF_TIME));
		TimeTable table = new TimeTable(t1, t2);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}

	@Test
	public void invert2Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		TimeSlot t1 = new TimeSlot(new StartTimePoint(new HospitalDate(
				1323327601000l)), new StopTimePoint(new HospitalDate(
				8984651322588l)));
		TimeSlot t2 = new TimeSlot(new StartTimePoint(new HospitalDate(
				12589765845329l)), new StopTimePoint(new HospitalDate(
				13569856245875l)));
		TimeSlot t3 = new TimeSlot(new StartTimePoint(
				HospitalDate.START_OF_TIME), new StopTimePoint(
				new HospitalDate(1323327601000l)));
		TimeSlot t4 = new TimeSlot(new StartTimePoint(new HospitalDate(
				8984651322588l)), new StopTimePoint(new HospitalDate(
				12589765845329l)));
		TimeSlot t5 = new TimeSlot(new StartTimePoint(new HospitalDate(
				13569856245875l)), new StopTimePoint(HospitalDate.END_OF_TIME));
		TimeTable table = new TimeTable(t2, t1);
		TimeTable table2 = new TimeTable(t3, t4, t5);
		TimeTable invertedTable = table.invert();
		assertTrue(invertedTable.equals(table2));
	}

	@Test
	public void invert3Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp21);
		TimeSlot t2 = new TimeSlot(tp10, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp21);
		TimeTable table = new TimeTable(t1, t2, t3);
		assertTrue(table.invert().invert().equals(table));
	}

	@Test
	public void invert4Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp21);
		TimeSlot t2 = new TimeSlot(tp10, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp51);
		TimeTable table = new TimeTable(t1, t2, t3);
		assertTrue(table.invert().invert().equals(table));
	}

	@Test
	public void invert5Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp10, tp21);
		TimeSlot t2 = new TimeSlot(tp10, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp51);
		TimeTable table = new TimeTable(t1, t2, t3);
		assertTrue(table.invert().invert().equals(table));
	}

	@Test
	public void getAllFreeSlots0Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp21);
		TimeSlot t2 = new TimeSlot(tp10, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp21);
		TimeSlot t4 = new TimeSlot(tp30, tp51);
		TimeTable table = new TimeTable(t1, t2, t3);
		TimeTable freeSlotsTable = new TimeTable(t4);
		assertTrue(freeSlotsTable.equals(table.getAllFreeSlots(0)));
	}

	@Test
	public void getAllFreeSlots1Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp21);
		TimeSlot t2 = new TimeSlot(tp10, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp21);
		TimeSlot t4 = new TimeSlot(tp30, tp51);
		TimeTable table = new TimeTable(t1, t2, t3);
		TimeTable freeSlotsTable = new TimeTable(t4);
		assertTrue(freeSlotsTable.equals(table.getAllFreeSlots(10000)));
	}

	@Test
	public void getAllFreeSlots2Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(tp20, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp21);
		TimeSlot t4 = new TimeSlot(tp30, tp51);
		TimeTable table = new TimeTable(t1, t2);
		TimeTable freeSlotsTable = new TimeTable(t3, t4);
		assertTrue(freeSlotsTable.equals(table.getAllFreeSlots(0)));
	}

	@Test
	public void getAllFreeSlots3Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(tp20, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp21);
		TimeSlot t4 = new TimeSlot(tp30, tp51);
		TimeTable table = new TimeTable(t1, t2);
		TimeTable freeSlotsTable = new TimeTable(t3, t4);
		assertTrue(freeSlotsTable.equals(table.getAllFreeSlots(5000)));
	}

	@Test
	public void getAllFreeSlots4Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(tp20, tp31);
		TimeSlot t3 = new TimeSlot(tp10, tp21);
		TimeSlot t4 = new TimeSlot(tp30, tp51);
		TimeTable table = new TimeTable(t1, t2);
		TimeTable freeSlotsTable = new TimeTable(t3, t4);
		assertFalse(freeSlotsTable.equals(table.getAllFreeSlots(5001)));
	}

	@Test
	public void intersectbyunionandinvert() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {

		TimePoint p = new StartTimePoint(new HospitalDate(
				HospitalDate.ONE_SECOND * 3));
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(p, tp31);

		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		(table.invert().getUnion(table2.invert())).invert();
	}

	@Test
	public void unionSpelen() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {

		TimePoint p = new StartTimePoint(new HospitalDate(
				HospitalDate.START_OF_TIME.getTimeSinceStart()
						+ HospitalDate.ONE_SECOND * 3));
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		TimeSlot t2 = new TimeSlot(p, tp31);

		TimeTable table = new TimeTable(t1);
		TimeTable table2 = new TimeTable(t2);
		table.invert().getIntersect(table2).equals(table.getIntersect(table2));
	}

	@Test
	public void cloneTest() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeTable t = new TimeTable();
		TimeSlot t1 = new TimeSlot(tp00, tp11);
		t.addTimeSlot(t1);
		TimeTable timeT2 = t.clone();

		assertTrue(t.equals(timeT2));
		assertTrue(t.getTimeSlots().get(0).equals(t1));
		assertFalse(t == timeT2);
		assertFalse(t.getTimeSlots().get(0) == t1);
	}

}