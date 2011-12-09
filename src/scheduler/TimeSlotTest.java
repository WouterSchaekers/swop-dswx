package scheduler;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;

public class TimeSlotTest
{
	@Test
	public void lengthTest()
	{
		TimeSlot s = new TimeSlot( new TimePoint(new Date(0), TimeType.start),new TimePoint(new Date(5), TimeType.end));
		assertTrue(s.getLength()==5);
	}
	@Test(expected = IllegalArgumentException.class)
	public void t(){
		TimeSlot s = new TimeSlot( new TimePoint(new Date(0), TimeType.end),new TimePoint(new Date(5), TimeType.start));
		
	}
	
	
}
