package ui.usecases;

import controllers.DischargePatientController;
import exceptions.DischargePatientException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import ui.UIData;
import ui.UseCase;

public class DischargePatient extends UseCase
{

	private DischargePatientController controller;

	public DischargePatient(UIData data) throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileException, InvalidConsultPatientFileController {
		super(data, 12);
	controller = new DischargePatientController(data.getLoginController(), data.getConsultPatientFileopenController());
	}

	@Override
	public UseCase execute() {
		System.out.println("Discharging patient.");
		try {
			controller.dischargePatient();
		} catch (DischargePatientException e) {
			printLn(e.getMessage());
			return mm();
		}
		printLn("Patient "+data.getConsultPatientFileopenController().getPatientFile().getPatientIN().getName()+" sucesfully discharged.");
		return mm();
	}

}
