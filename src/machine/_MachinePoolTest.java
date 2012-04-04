package machine;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import system.Campus;
import system.Hospital;
import system.StandardHospitalBuilder;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class _MachinePoolTest
{
	MachinePool pool;
	Campus campus;
	@Before
	public void setup()
	{
		Hospital hospital = new StandardHospitalBuilder().build();
		campus = hospital.getAllCampuses().iterator().next();
		pool = campus.getMachinePool();
	}
	@Test
	public void TestNames()
	{
		int i =0;
		String location = "Location";
		for(MachineBuilder builder:pool.getAllBuilders())
		{
			i++;
			String l= location+i;
			builder.setLocation(campus);
			builder.setLocationWithinCampus(l);
			builder.setSerial(i);
			boolean succes =true;
			Machine machine = null;
			try {
				machine=	pool.addMachine(builder);
			} catch (InvalidLocationException e) {
				succes =false;
			} catch (InvalidSerialException e) {
				succes =false;
			}
			succes =true;
			if(!succes)
				continue;
			assertTrue(machine.getCampusLocation()==campus);
			assertTrue(machine.getLocationWithinCampus().equals(l));
			assertTrue(machine.getSerial()==i);
			
			assertTrue(succes);
			builder.toString();
		}
		pool.getAllMachines();
		pool.equals(pool);
		pool.toString();
	}
}
