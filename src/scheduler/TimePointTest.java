package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class TimePointTest
{
	@Test
	public void basicTestOne()
	{
		TimePoint p = new TimePoint(new HospitalDate(0),TimeType.stop);
		assertTrue(p.getTime()==HospitalDate.START_OF_TIME.getTimeSinceStart());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTestOne()
	{
		TimePoint p = new TimePoint(null,TimeType.stop);

	}
	@Test(expected = IllegalArgumentException.class)
	public void nullTestTwo()
	{
		TimePoint p = new TimePoint(new HospitalDate(0),null);

	}
}
