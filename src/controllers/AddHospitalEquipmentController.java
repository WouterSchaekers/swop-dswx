package controllers;

import java.util.Collection;
import java.util.LinkedList;
import machine.MachineBuilder;
import system.Campus;
import system.Location;
import users.HospitalAdmin;
import users.User;
import controllers.interfaces.LocationIN;
import exceptions.ControllerException;
import exceptions.InvalidCampusException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSerialException;

/**
 * Allows you to add new equipment to the hospital.
 */
@controllers.PUBLICAPI
public class AddHospitalEquipmentController extends NeedsLoginController
{
	/**
	 * Default constructor to add hospital equipment to the hospital.
	 * 
	 * @param loginController
	 * @throws ControllerException
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalException
	 */
	@controllers.PUBLICAPI
	public AddHospitalEquipmentController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * @return All the objects that can create a kinds of different machines.
	 * @throws InvalidCampusException 
	 */
	@controllers.PUBLICAPI
	public Collection<MachineBuilder> getAllMachineBuilders(LocationIN campus) throws InvalidCampusException {
		if(!(campus instanceof Campus))
		{
			throw new InvalidCampusException("location object provided was not a campus");
		}
		Campus c=(Campus)campus;
		return c.getMachinePool().getAllBuilders();
	}

	/**
	 * Creates a new machine from the given machine builder and adds it to the
	 * hospital.
	 */
	@controllers.PUBLICAPI
	public void createMachine(MachineBuilder b, LocationIN whereabouts) throws InvalidLocationException,
			InvalidSerialException {
		((Campus) whereabouts).getMachinePool().addMachine(b);
	}

	/**
	 * @return all possible locations in this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<LocationIN> getAllLocations() {
		Collection<LocationIN> rv = new LinkedList<LocationIN>();
		for (Location l : hospital.getAllCampuses())
			rv.add(l);
		return rv;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}