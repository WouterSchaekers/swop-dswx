package scheduler;

import static org.junit.Assert.*;
import java.sql.Date;
import org.junit.Test;

public class TimePointTest
{
	@Test
	public void basicTestOne()
	{
		TimePoint p = new TimePoint(new Date(0),TimeType.end);
		assertTrue(p.getTime()==0);
	}

	@Test(expected =IllegalArgumentException.class)
	public void nullTestOne()
	{
		TimePoint p = new TimePoint(null,TimeType.end);

	}
	@Test(expected =IllegalArgumentException.class)
	public void nullTestTwo()
	{
		TimePoint p = new TimePoint(new Date(0),null);

	}
}
