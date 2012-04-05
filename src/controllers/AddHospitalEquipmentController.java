package controllers;

import java.util.Collection;
import java.util.LinkedList;
import machine.MachineBuilder;
import system.Campus;
import system.Location;
import users.HospitalAdmin;
import users.User;
import controllers.interfaces.CampusIN;
import controllers.interfaces.LocationIN;
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
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The logincontroller of the user trying to add hospital
	 *            equipment.
	 * @throws InvalidLoginControllerException
	 *             If the use to whom the given logincontroller belongs is not a
	 *             hospital administrator or is invalid in another way.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	@controllers.PUBLICAPI
	public AddHospitalEquipmentController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * @return Objects that can create different kinds of machines.
	 * @throws InvalidCampusException
	 */
	@controllers.PUBLICAPI
	public Collection<MachineBuilder> getAllMachineBuilders() {
		return getAllMachineBuilders(loginController_.getLocation());
	}

	/**
	 * Private method to return all builders that are on the campus that the
	 * user who has created this controller is on.
	 * 
	 * @param campus
	 *            The campus the owner of this controller is on.
	 * @return All builders present on the given campus.
	 */
	private Collection<MachineBuilder> getAllMachineBuilders(CampusIN campus) {
		Campus c = (Campus) campus;
		return c.getMachinePool().getAllBuilders();
	}

	/**
	 * Creates a new machine from the given machine builder and adds it to the
	 * hospital.
	 * 
	 * @throws InvalidSerialException
	 * @throws InvalidLocationException
	 */
	@controllers.PUBLICAPI
	public void createMachine(MachineBuilder b) throws InvalidLocationException, InvalidSerialException {
		createMachine(b, loginController_.getLocation());
	}

	/**
	 * Private method for adding machines to the location the owner of this
	 * controller is at.
	 * 
	 * @param b
	 *            The builder with initialised fields.
	 * @param whereabouts
	 *            The location of the owner of this controller.
	 * @throws InvalidLocationException
	 *             If the location set in the machine builder is not a valid
	 *             one.
	 * @throws InvalidSerialException
	 *             If the serial set in the given machine builder is not a valid
	 *             one.
	 */
	private void createMachine(MachineBuilder b, LocationIN whereabouts) throws InvalidLocationException,
			InvalidSerialException {
		((Campus) whereabouts).getMachinePool().addMachine(b);
	}

	/**
	 * @return All existing locations in this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<LocationIN> getAllLocations() {
		Collection<LocationIN> rv = new LinkedList<LocationIN>();
		for (Location l : hospital.getAllCampuses())
			rv.add(l);
		return rv;
	}

	/**
	 * @return True if u is a hospital administrator.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}