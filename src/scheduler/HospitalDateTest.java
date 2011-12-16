package scheduler;

import static org.junit.Assert.*;
import org.junit.Test;

public class HospitalDateTest
{
	@Test
	public void Constructor0Test(){
		HospitalDate h = new HospitalDate();
		assertTrue(h.getYear() == HospitalDate.START_YEAR);
		assertTrue(h.getMonth() == HospitalDate.START_MONTH);
		assertTrue(h.getDay() == HospitalDate.START_DAY);
		assertTrue(h.getHour() == HospitalDate.START_HOUR);
		assertTrue(h.getMinute() == HospitalDate.START_MINUTE);
		assertTrue(h.getSecond() == HospitalDate.START_SECOND);
	}
	
	@Test
	public void Constructor1Test(){
		HospitalDate h = new HospitalDate(1000000);
		assertTrue(h.getTotalMillis() == HospitalDate.START_OF_TIME.getTotalMillis()+1000000);
	}
	@Test
	public void cloneTest()
	{
		HospitalDate h = new HospitalDate(1000000);
		assertTrue(h.equals(h.clone()));
		
	}
	
	@Test
	public void setTimeTest(){
		HospitalDate h = new HospitalDate();
		h.setYear(2011);
		h.setMonth(11);
		h.setDay(11);
		h.setHour(11);
		h.setMinute(11);
		h.setSecond(11);
		assertTrue(h.getYear() == 2011);
		assertTrue(h.getMonth() == 11);
		assertTrue(h.getDay() == 11);
		assertTrue(h.getHour() == 11);
		assertTrue(h.getMinute() == 11);
		assertTrue(h.getSecond() == 11);
	}
	
	@Test
	public void toStringTest(){
		System.out.println(new HospitalDate(0));
	}
	
	@Test
	public void beforeTest(){
		HospitalDate h0 = new HospitalDate(1000);
		HospitalDate h1 = new HospitalDate(1000);
		HospitalDate h2 = new HospitalDate(1001);
		assertFalse(h0.before(h1));
		assertTrue(h0.before(h2));
		assertFalse(h2.before(h0));
	}
}