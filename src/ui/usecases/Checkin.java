package ui.usecases;

import java.util.Collection;
import controllers.CheckinController;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.NoSuchPatientException;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;

public class Checkin extends UseCase
{

	private CheckinController controller;

	public Checkin(UIData data ) throws InvalidLoginControllerException, InvalidHospitalException{
		super(data, 1);
		controller = new CheckinController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		printLn("Checkin a patient:");
		
		{
			Collection<PatientFileIN> patientFiles =controller.getAllPatientFiles();
			printLn("Select a patientFile, enter with no argumenst to cancel.");
			Selector<PatientFileIN> patientFileSelector = new Selector<PatientFileIN>(patientFiles, new Displayer<PatientFileIN>()
			{

				@Override
				public void display(PatientFileIN t) {
				print("Patient file for:"+t.getPatientIN().getName());
				}
			});
			PatientFileIN file = patientFileSelector.get();
			if(file==null)
			{
				try {
					return new Checkin(data);
				} catch (InvalidLoginControllerException e) {
				;
				} catch (InvalidHospitalException e) {
					;
				}
			}
			printLn("Patient file for patient:"+file.getPatientIN().getName());
			try {
				controller.checkIn(file);
			} catch (NoSuchPatientException e) {
				System.out.println("Patientfile does not exist!");
				return new MainMenu(data);
			} catch (InvalidPatientFileException e) {
				printLn("Patientfile is wrong.");
				return new MainMenu(data);
			}
			printLn("Sucesfully checked in!");
			return new MainMenu(data);
		}
	}
	@Override
	public String toString()
	{
		return "Checkin";
	}

}
