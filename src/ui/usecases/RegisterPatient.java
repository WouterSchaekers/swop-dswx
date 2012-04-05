package ui.usecases;

import controllers.RegisterPatientController;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;

public class RegisterPatient extends UseCase
{

	private RegisterPatientController controller;

	public RegisterPatient(UIData data) throws InvalidLoginControllerException, InvalidHospitalException {
		super(data, 4);
		controller = new RegisterPatientController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		printLn("Create new Patient in hospital system.");
		printLn("Please submit the neccesary information for the patient, (name must be unique and not empty).");
		print("Name name:");
		String name = read();
		PatientFileIN created ;
		try {
		created=	controller.registerNewPatient(name);
		} catch (InvalidNameException e) {
			printLn("Patient name is invalid "+e.getMessage());
			return new MainMenu(data);
		} catch (InvalidPatientFileException e) {
			printLn("Patient name already exists in hospital.");
			return new MainMenu(data);
		}
		printLn("Patient "+created.getPatientIN().getName()+" was sucesfully created.");
		return new MainMenu(data);
	}

}
