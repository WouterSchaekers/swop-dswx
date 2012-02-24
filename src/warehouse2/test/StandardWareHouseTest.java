package warehouse2.test;

import static org.junit.Assert.*;
import org.junit.Test;
import scheduler.HospitalDate;
import warehouse2.StandardHospitalWareHouse;
import warehouse2.WareHouse;
import warehouse2.WareHouseOverFlowException;
import warehouse2.items.SleepingTablet;
import warehouse2.items.WareHouseItem;

public class StandardWareHouseTest
{
	private WareHouseItem sleepingT()
	{
		return new SleepingTablet(new HospitalDate());
	}
	@Test
	public void initTest()
	{
		WareHouse w = new StandardHospitalWareHouse();
		
	}
	@Test
	public void addOneItem() throws WareHouseOverFlowException
	{
		WareHouse w = new StandardHospitalWareHouse();
		w.add(sleepingT());
		w.add(sleepingT());
		w.add(sleepingT());
		assertTrue(w.getCurrentCountFor(sleepingT())==3);
	}
	@Test(expected = WareHouseOverFlowException.class)
	public void addOneToMany() throws WareHouseOverFlowException
	{
		WareHouse w = new StandardHospitalWareHouse();
		for(int i = 0 ; i < w.getMaxCount(sleepingT())+1;i ++)
			w.add(sleepingT());
	}
}
