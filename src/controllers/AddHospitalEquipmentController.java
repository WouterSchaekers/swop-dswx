package controllers;

import java.util.Collection;
import machine.MachineBuilder;
import machine.MachinePool;
import users.HospitalAdmin;
import controllers.interfaces.UserIN;
import exceptions.ControllerException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class AddHospitalEquipmentController
{
	MachinePool machinePool;
	LoginController loginControler;

	/**
	 * Default constructor to add hospital equipment to the hospital system All
	 * calls on this controller have to happen with the same loginController
	 * 
	 * @param loginController
	 * @throws ControllerException
	 * @throws InvalidLoginControllerException
	 */
	public AddHospitalEquipmentController(LoginController loginController,
			DataPasser passer) throws InvalidLoginControllerException {
		if (!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		this.loginControler = loginController;
		this.machinePool = passer.getMachinePool();

	}

	/**
	 * Checks if the provided logingcontroller provides sufficient rights to
	 * create/use this hospitalEquipementController
	 * 
	 * @param loginController
	 * @return
	 */
	private boolean isValidLoginController(LoginController loginController) {
		UserIN i = loginController.getUser();
		if (i == null)
			return false;
		if (!(i instanceof HospitalAdmin))
			return false;
		if (this.loginControler == null)
			return true;
		else if (!this.loginControler.equals(loginController)) {
			return false;
		}
		return true;
	}

	public Collection<MachineBuilder> getAllMachines(
			LoginController loginController)
			throws InvalidLoginControllerException {
		if (!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		return machinePool.getAllBuilders();
	}

	public void createMachine(MachineBuilder b, int serial, String location,
			LoginController loginController)
			throws InvalidLoginControllerException, InvalidLocationException,
			InvalidSerialException, InvalidTimeSlotException {
		if (!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		machinePool.addMachine(b.build(serial, location));

	}

}
