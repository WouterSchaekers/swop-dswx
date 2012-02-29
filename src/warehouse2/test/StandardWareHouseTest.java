package warehouse2.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import scheduler.HospitalDate;
import warehouse2.StandardHospitalWareHouse;
import warehouse2.Warehouse;
import warehouse2.WareHouseOverFlowException;
import warehouse2.items.SleepingTablet;
import warehouse2.items.EpirableWarehouseItem;

public class StandardWareHouseTest
{
	private EpirableWarehouseItem sleepingT() {
		return new SleepingTablet(new HospitalDate());
	}

	@Test
	public void initTest() throws WareHouseOverFlowException {
		Warehouse w = new StandardHospitalWareHouse();
		w.add(null);
	}

	@Test
	public void addOneItem() throws WareHouseOverFlowException {
		Warehouse w = new StandardHospitalWareHouse();
		w.add(sleepingT());
		w.add(sleepingT());
		w.add(sleepingT());
		assertTrue(w.getCurrentCountFor(sleepingT().getClass()) == 3);
	}

	@Test(expected = WareHouseOverFlowException.class)
	public void addOneToMany() throws WareHouseOverFlowException {
		Warehouse w = new StandardHospitalWareHouse();
		for (int i = 0; i < w.getMaxCount(sleepingT().getClass()) + 1; i++)
			w.add(sleepingT());
	}
}
