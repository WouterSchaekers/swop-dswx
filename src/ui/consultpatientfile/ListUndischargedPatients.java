package ui.consultpatientfile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.PatientFileOpenController;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidLoginControllerException;

public class ListUndischargedPatients extends ConsultPatientFileSuperclass
{
	public ListUndischargedPatients(UserinterfaceData data,
			ConsutlPatientFileData d) {
		super(data, d);

	}

	@Override
	public Usecase Execute() {
		PatientFileOpenController c;
		try {
			c = new PatientFileOpenController(data.getDataPasser(),
					data.getLoginController());
		} catch (InvalidLoginControllerException e) {
			System.out.println("You are not allowed to do this");
			return new SelectUsecase(data);
		}
		chaindata.setPatientFileOpenController(c);

		Map<String, PatientFileIN> map = new HashMap<String, PatientFileIN>();
		Collection<PatientFileIN> patientfiles = chaindata
				.getPatientfileOpenController().getAllPatientFiles(
						data.getLoginController());
		if (patientfiles.isEmpty()) {
			System.out
					.println("No patients registered in this hospital, sorry!");
			return new SelectUsecase(data);
		}
		for (PatientFileIN file : patientfiles) {
			map.put(file.getName(), file);
			System.out.println(file.getName());
		}
		System.out
				.println("Q to quit, enter the patients name to select his patient file.");
		String patientName = input.nextLine();
		if (patientName.equalsIgnoreCase("q"))
			return new SelectUsecase(data);
		if (!map.containsKey(patientName)) {
			System.out.println("invalid input, user not found try again!");
			return this;
		} else {
			try {
				chaindata.getPatientfileOpenController().openPatientFile(
						map.get(patientName), data.getLoginController());
			} catch (InvalidLoginControllerException e) {
				System.out.println("Login exception, not allowed to do this");
				return new SelectUsecase(data);
			}
		}
		data.setRegpatctrl(chaindata.getPatientfileOpenController());
		System.out.println("Patient :"
				+ chaindata.getPatientfileOpenController().getPatientFile()
						.getName() + "'s file opened succesfully!");
		return new SelectUsecase(data);
	}

}
