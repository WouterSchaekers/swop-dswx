package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class TimeSlotTest
{
	@Test
	public void lengthTest() {
		TimeSlot s = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(5)));
		assertTrue(s.getLength() == 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stopBeforeStartTestFail() {
		TimeSlot s = new TimeSlot(new StartTimePoint(new HospitalDate(5)),
				new EndTimePoint(new HospitalDate(0)));
	}
	
	@Test
	public void overlapsTest(){
		TimeSlot s0 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(5)));
		TimeSlot s1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(5)));
		assertTrue(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps1Test(){
		TimeSlot s0 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(5)));
		TimeSlot s1 = new TimeSlot(new StartTimePoint(new HospitalDate(1)),
				new EndTimePoint(new HospitalDate(6)));
		assertTrue(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps2Test(){
		TimeSlot s0 = new TimeSlot(new StartTimePoint(new HospitalDate(4)),
				new EndTimePoint(new HospitalDate(9)));
		TimeSlot s1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(5)));
		assertTrue(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps3Test(){
		TimeSlot s0 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(5)));
		TimeSlot s1 = new TimeSlot(new StartTimePoint(new HospitalDate(5)),
				new EndTimePoint(new HospitalDate(10)));
		assertFalse(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps4Test(){
		TimeSlot s0 = new TimeSlot(new StartTimePoint(new HospitalDate(5)),
				new EndTimePoint(new HospitalDate(10)));
		TimeSlot s1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(5)));
		assertFalse(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps5Test(){
		TimeSlot s0 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(1000)));
		TimeSlot s1 = new TimeSlot(new StartTimePoint(new HospitalDate(1001)),
				new EndTimePoint(new HospitalDate(2001)));
		assertFalse(s0.overlaps(s1));
	}
	
	@Test
	public void overlaps6Test(){
		TimeSlot s0 = new TimeSlot(new StartTimePoint(new HospitalDate(1001)),
				new EndTimePoint(new HospitalDate(2001)));
		TimeSlot s1 = new TimeSlot(new StartTimePoint(new HospitalDate(0)),
				new EndTimePoint(new HospitalDate(1000)));
		assertFalse(s0.overlaps(s1));
	}
}