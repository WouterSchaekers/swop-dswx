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
		TimePoint p = new StopTimePoint(o);

	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTestFour() {
		TimePoint p = new StopTimePoint(-1);

	}
	@Test
	public void comparatorTest1(){
		TimePoint one = new StartTimePoint(new HospitalDate(10));
		TimePoint two = new StopTimePoint(new HospitalDate(10));
		assertTrue(TimePoint.ComparatorsEndFirst.compare(one, two)==-1);
		assertFalse(TimePoint.ComparatorsStartFirst.compare(one, two)==-1);

	}
	@Test
	public void comparatorTest2(){
		TimePoint two = new StartTimePoint(new HospitalDate(10));
		TimePoint one = new StopTimePoint(new HospitalDate(10));
		assertTrue(TimePoint.ComparatorsEndFirst.compare(one, two)==1);
		assertFalse(TimePoint.ComparatorsStartFirst.compare(one, two)==1);

	}
}
