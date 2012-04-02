package controllers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import machine.MachineBuilder;
import system.Campus;
import system.Location;
import users.HospitalAdmin;
import users.User;
import controllers.interfaces.LocationIN;
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
	 */
	public Collection<MachineBuilder> getAllMachineBuilders() {
		Iterator<Campus> campIterator = hospital.getAllCampuses().iterator();

		if (campIterator.hasNext())
			return (campIterator.next().getMachinePool().getAllBuilders());
		return null;
	}

	/**
	 * Creates a new machine from the given machine builder and adds it to the
	 * hospital.
	 */
	public void createMachine(MachineBuilder b, Location whereabouts) throws InvalidLocationException,
			InvalidSerialException {

		((Campus) (whereabouts)).getMachinePool().addMachine(b);
	}

	/**
	 * @return all possible locations in this hospital.
	 */
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