package DietersSandbox;

import java.util.Collection;
import java.util.Iterator;
import DietersSandbox.domainman.DomainObject;
import DietersSandbox.domainman.DomainObjectManager;
import machine.MachineBuilder;
import system.Campus;
import system.Whereabouts;
import users.HospitalAdmin;
import users.User;
import exceptions.ControllerException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSerialException;

/**
 * Allows you to add new equipment to the hospital.
 */
public class AddHospitalEquipmentController extends NeedsLoginController
{
	private DomainObjectManager<MachineBuilder> dom= new DomainObjectManager<MachineBuilder>();
	/**
	 * Default constructor to add hospital equipment to the hospital.
	 * 
	 * @param loginController
	 * @throws ControllerException
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalException
	 */
	public AddHospitalEquipmentController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * @return All the objects that can create a kinds of different machines.
	 * 
	 */
	public Collection<MachineBuilder> getAllMachineBuilders() {
		Iterator<Campus> campIterator = hospital.getAllCampusses().iterator();
		if(campIterator.hasNext())
			return(campIterator.next().getMachinePool().getAllBuilders());
		return null;
	}
	public Collection<DomainObject<MachineBuilder>> getAllMachineBuilder()
	{
		Iterator<Campus> campIterator = hospital.getAllCampusses().iterator();
		if(campIterator.hasNext())
			return dom.transform(campIterator.next().getMachinePool().getAllBuilders());
		return null;
	}

	/**
	 * Creates a new machine from the given machine builder and adds it to the
	 * hospital.
	 */
	public void createMachine(MachineBuilder b, int serial, String location, Whereabouts whereabouts)
			throws InvalidLocationException, InvalidSerialException {
		((Campus)(whereabouts)).getMachinePool().addMachine(b.build(serial, location));
	}
	public void createMachine(DomainObject<MachineBuilder> builder, int serial, String location, Whereabouts whereabouts) throws InvalidLocationException, InvalidSerialException, Exception
	{
		createMachine(dom.get(builder), serial, location, whereabouts);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}