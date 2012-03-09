package controllers;

import java.util.Collection;
import machine.MachineBuilder;
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
	 * @return All the objects that can create a kind of machines 
	 * 
	 */
	public Collection<MachineBuilder> getAllMachineBuilders() {
		return hospital.getMachinePool().getAllBuilders();
	}

	/**
	 * Creates a new machine from the given machine builder and adds it to the
	 * hospital.
	 */
	public void createMachine(MachineBuilder b, int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		hospital.getMachinePool().addMachine(b.build(serial, location));
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}