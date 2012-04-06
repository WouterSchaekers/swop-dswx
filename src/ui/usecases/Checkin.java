package ui.usecases;

import java.util.Collection;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.CheckinController;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;

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
			if(patientFiles.isEmpty())
			{
				printLn("There are no patients.");
				return mm();
			}
			patientFiles.iterator().next().isDischarged();
			printLn("Select a patientFile.");
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
			}  catch (InvalidPatientFileException e) {
				printLn(" is wrong.");
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
