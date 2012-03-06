package controllers;

import java.util.Collection;
import machine.MachineBuilder;
import system.Hospital;
import users.HospitalAdmin;
import users.User;
import exceptions.*;

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
	public AddHospitalEquipmentController(LoginController loginController,
			Hospital hospital) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, loginController);
	}

	/**
	 * @return All the machines currently in the hospital.
	 */
	public Collection<MachineBuilder> getAllMachines() {
		return hospital.getMachinePool().getAllBuilders();
	}

	/**
	 * Creates a new machine from the given machine builder and adds it to the
	 * hospital.
	 */
	// TODO : Fix these parameters
	public void createMachine(MachineBuilder b, int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		hospital.getMachinePool().addMachine(b.build(serial, location));
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}