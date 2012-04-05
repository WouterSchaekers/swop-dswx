package ui.usecases;

import java.util.Collection;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.ConsultPatientFileController;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
public class ConsultPatientFile extends UseCase
{
	ConsultPatientFileController consultPatienFileController;

	public ConsultPatientFile(UIData data) throws InvalidHospitalException, InvalidLoginControllerException {
		super(data, 45656);
		consultPatienFileController = new ConsultPatientFileController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		System.out.println("Welcome to selecting a patient !");
		Collection<PatientFileIN> patientfiles =consultPatienFileController.getAllPatientFiles();
		Selector<PatientFileIN> patienfileSelector = new Selector<PatientFileIN>(patientfiles, new Displayer<PatientFileIN>()
		{
			@Override
			public void display(PatientFileIN file)
			{
				print("Patient :"+file.getPatientIN());
			}
		});
		PatientFileIN selected = patienfileSelector.get();
		consultPatienFileController.openPatientFile(selected);
		printLn("Patient"+selected.getPatientIN().getName()+" sucesfully selected:");
		data.setConsultPatientFileopenController(consultPatienFileController);
		return new MainMenu(data);
	}
	@Override
	public String toString()
	{
		return "Consult patientfile";
	}
}
