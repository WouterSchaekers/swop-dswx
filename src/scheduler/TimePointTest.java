package scheduler;

import static org.junit.Assert.*;
import scheduler.Date;
import org.junit.Test;

@SuppressWarnings("unused")
public class TimePointTest
{
	@Test
	public void basicTestOne()
	{
		TimePoint p = new TimePoint(new Date(0),TimeType.stop);
		assertTrue(p.getTime()==0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTestOne()
	{
		TimePoint p = new TimePoint(null,TimeType.stop);

	}
	@Test(expected = IllegalArgumentException.class)
	public void nullTestTwo()
	{
		TimePoint p = new TimePoint(new Date(0),null);

	}
}
