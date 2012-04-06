package warehouse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import scheduler.HospitalDate;
import system.Campus;
import system.Hospital;
import system.StandardHospitalBuilder;
import warehouse.item.ActivatedCarbonType;
import warehouse.item.AsprinType;
import warehouse.item.MealType;
import warehouse.item.MiscType;
import warehouse.item.PlasterType;
import warehouse.item.SleepingTabletsType;
import warehouse.item.VitaminType;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidWarehouseItemException;
import exceptions.WarehouseOverCapacityException;

/**
 * @author <pre>
                     .-"""-.
                   _/-=-.   \
                  (_|a a/   |_
                   / "  \   ,_)
              _    \`=' /__/
             / \_  .;--'  `-.
             \___)//   I  ,  \
              \ \/;  love  \  \
               \_.| Coding  | |
                .-\ '     _/_/
              .'  _;.    (_  \
             /  .'   `\   \\_/
            |_ /       |  |\\
           /  _)       /  / ||
          /  /       _/  /  //
          \_/       ( `-/  ||
                    /  /   \\ .-.
                    \_/     \'-'/
                             `"`</pre>
 */
public class _WarehouseTest
{
	private Warehouse warehouse;
	private Hospital hospital;
	private Campus campus;
	
	@Before
	public void setUp()
	{
		hospital = new StandardHospitalBuilder().build();
		campus = hospital.getAllCampuses().iterator().next();
		warehouse = campus.getWarehouse();
	}
	
	@Test
	public void testInit()
	{
		assertEquals(10, warehouse.getCurrentCount(new AsprinType()));
		assertEquals(10, warehouse.getCurrentCount(new VitaminType()));
		assertEquals(10, warehouse.getCurrentCount(new MiscType()));
		assertEquals(10, warehouse.getCurrentCount(new SleepingTabletsType()));
		
		assertEquals(8, warehouse.getCurrentCount(new PlasterType()));
		assertEquals(120, warehouse.getCurrentCount(new MealType()));
		
		assertEquals(campus, warehouse.getCampus());
	}
	
	@Test
	public void testTakeAdd() throws Exception
	{
		int aantalAspirines = warehouse.getCurrentCount(new AsprinType());
		assertEquals(10, aantalAspirines);
		
		warehouse.take(new AsprinType());
		aantalAspirines = warehouse.getCurrentCount(new AsprinType());
		assertEquals(9, aantalAspirines);
		
		warehouse.add(new AsprinType(), getExpireDateAspirin());
		aantalAspirines = warehouse.getCurrentCount(new AsprinType());
		assertEquals(10, aantalAspirines);
	}
	
	@Test(expected = WarehouseOverCapacityException.class)
	public void testAddOverFull() throws Exception
	{
		warehouse.add(new AsprinType(), getExpireDateAspirin());
	}
	
	@Test
	public void testGetMaxAmount() throws Exception
	{
		int aantal = warehouse.getMaxCount(new AsprinType());
		assertEquals(10, aantal);		
		assertEquals(-1, warehouse.getMaxCount(null));
		
		WarehouseItemType unknown = new WarehouseItemType() {

			@Override
			public WarehouseItem create(HospitalDate expirydate) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getTimeToLive() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String name() {
				// TODO Auto-generated method stub
				return null;
			}};
		
		assertEquals(-1, warehouse.getMaxCount(unknown));
	}
	
	@Test(expected = InvalidWarehouseItemException.class)
	public void setMaxAmountAlreadySet() throws Exception
	{
		warehouse.setMaxCount(new AsprinType(), 9);
	}
	
	@Test(expected = NullPointerException.class)
	public void setMaxAmountNullType() throws Exception
	{
		warehouse.setMaxCount(null, 8);
	}
	
	public HospitalDate getExpireDateAspirin()
	{
		return new HospitalDate(hospital.getTimeKeeper().getSystemTime().getTimeSinceStart() + new AsprinType().getTimeToLive());
	}
	
	@Test(expected = InvalidWarehouseItemException.class)
	public void testTakeNullType() throws Exception
	{
		warehouse.take(null);
	}
	
	@Test
	public void testHas()
	{
		assertTrue(warehouse.has(new AsprinType(), 9));
		assertTrue(warehouse.has(new AsprinType(), 10));
		assertFalse(warehouse.has(new AsprinType(), 11));
	}
	
	@Test
	public void testHasNullType()
	{
		assertFalse(warehouse.has(null, 11));
	}
	
	@Test
	public void testHasNegatieveAmount()
	{
		assertTrue(warehouse.has(new AsprinType(), -1));
	}
	
	@Test
	public void testGetAvailableItemTypes()
	{
		Collection<WarehouseItemType> types = warehouse.getAvailableItemTypes();
		
		assertTrue(filterType(types, new AsprinType()));
		assertTrue(filterType(types, new ActivatedCarbonType()));
		assertTrue(filterType(types, new MiscType()));
		assertTrue(filterType(types, new MealType()));
		assertTrue(filterType(types, new VitaminType()));
		assertTrue(filterType(types, new SleepingTabletsType()));
		assertTrue(filterType(types, new PlasterType()));
		assertTrue(types.isEmpty());
	}
	
	private boolean filterType(Collection<WarehouseItemType> types, WarehouseItemType toFilter)
	{
		if(types.contains(toFilter))
		{
			types.remove(toFilter);
			return true;
		}
		
		return false;
	}
}
