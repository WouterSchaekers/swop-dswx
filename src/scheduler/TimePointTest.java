package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class TimePointTest
{
	@Test
	public void basicTestOne() {
		TimePoint p = new StartTimePoint(new HospitalDate());
		assertTrue(p.getTime() == HospitalDate.START_OF_TIME
				.getTimeSinceStart());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTestOne() {
		HospitalDate o = null;
		TimePoint p = new StartTimePoint(o);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTestTwo() {
		TimePoint p = new StartTimePoint(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTestThree() {
		HospitalDate o = null;
		TimePoint p = new EndTimePoint(o);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTestFour() {
		TimePoint p = new EndTimePoint(-1);

	}
}
