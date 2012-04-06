package ui.usecases;

import java.util.Collection;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.ConsultPatientFileController;
import controllers.interfaces.PatientFileIN;
public class ConsultPatientFile extends UseCase
{
	ConsultPatientFileController consultPatienFileController;

	public ConsultPatientFile(UIData data) throws Exception {
		super(data, 45656);
		if(data.getConsultPatientFileopenController()!=null)
			throw new Exception();
		consultPatienFileController = new ConsultPatientFileController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		System.out.println("Welcome to selecting a patient !");
		Collection<PatientFileIN> patientfiles =consultPatienFileController.getAllPatientFiles();
		if(patientfiles.isEmpty())
		{
			printLn("There are no patients in this hospital.");
			return mm();
		}
		Selector<PatientFileIN> patienfileSelector = new Selector<PatientFileIN>(patientfiles, new Displayer<PatientFileIN>()
		{
			@Override
			public void display(PatientFileIN file)
			{
				print("Patient :"+file.getPatientIN().getName());
			}
		});
		PatientFileIN selected = patienfileSelector.get();
		consultPatienFileController.openPatientFile(selected);
		printLn("Patient "+selected.getPatientIN().getName()+" sucesfully selected:");
		data.setConsultPatientFileopenController(consultPatienFileController);
		return new MainMenu(data);
	}
	@Override
	public String toString()
	{
		return "Consult patientfile";
	}
}
