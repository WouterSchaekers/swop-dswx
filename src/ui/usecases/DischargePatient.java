package ui.usecases;

import controllers.DischargePatientController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;
import ui.UIData;
import ui.UseCase;

public class DischargePatient extends UseCase
{

	private DischargePatientController controller;

	public DischargePatient(UIData data) throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController, InvalidPatientFileException {
		super(data, 12);
	controller = new DischargePatientController(data.getLoginController(), data.getConsultPatientFileopenController());
	}

	@Override
	public UseCase execute() {
		System.out.println("");
		return null;
	}

}
