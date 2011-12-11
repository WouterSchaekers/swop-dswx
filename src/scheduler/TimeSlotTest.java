package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class TimeSlotTest
{
	@Test
	public void lengthTest() {
		TimeSlot s = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.start),
				new TimePoint(new HospitalDate(5), TimeType.stop));
		assertTrue(s.getLength() == 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stopBeforeStartTestFail() {
		TimeSlot s = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.stop),
				new TimePoint(new HospitalDate(5), TimeType.start));
	}
	
	@Test
	public void overlapsTest(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.start),
				new TimePoint(new HospitalDate(5), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.start),
				new TimePoint(new HospitalDate(5), TimeType.stop));
		assertTrue(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps1Test(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.start),
				new TimePoint(new HospitalDate(5), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new HospitalDate(1), TimeType.start),
				new TimePoint(new HospitalDate(6), TimeType.stop));
		assertTrue(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps2Test(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.start),
				new TimePoint(new HospitalDate(5), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new HospitalDate(-4), TimeType.start),
				new TimePoint(new HospitalDate(1), TimeType.stop));
		assertTrue(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps3Test(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.start),
				new TimePoint(new HospitalDate(5), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new HospitalDate(5), TimeType.start),
				new TimePoint(new HospitalDate(10), TimeType.stop));
		assertFalse(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps4Test(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new HospitalDate(5), TimeType.start),
				new TimePoint(new HospitalDate(10), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new HospitalDate(0), TimeType.start),
				new TimePoint(new HospitalDate(5), TimeType.stop));
		assertFalse(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps5Test(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new HospitalDate(-1000), TimeType.start),
				new TimePoint(new HospitalDate(0), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new HospitalDate(1), TimeType.start),
				new TimePoint(new HospitalDate(1001), TimeType.stop));
		assertFalse(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps6Test(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new HospitalDate(1), TimeType.start),
				new TimePoint(new HospitalDate(1001), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new HospitalDate(-1000), TimeType.start),
				new TimePoint(new HospitalDate(0), TimeType.stop));
		assertFalse(s0.overlaps(s1));
	}
}