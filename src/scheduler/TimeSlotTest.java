package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class TimeSlotTest
{
	@Test
	public void lengthTest() {
		TimeSlot s = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		assertTrue(s.getLength() == 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stopBeforeStartTestFail() {
		TimeSlot s = new TimeSlot(new TimePoint(new Date(0), TimeType.stop),
				new TimePoint(new Date(5), TimeType.start));
	}
	
	@Test
	public void overlapsTest(){
		TimeSlot s0 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		TimeSlot s1 = new TimeSlot(new TimePoint(new Date(0), TimeType.start),
				new TimePoint(new Date(5), TimeType.stop));
		assertTrue(s0.overlaps(s1));
	}
}