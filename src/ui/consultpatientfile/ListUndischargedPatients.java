package ui.consultpatientfile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.ConsultPatientFileController;
import controllers.interfaces.PatientFileIN;

public class ListUndischargedPatients extends ConsultPatientFileSuperclass
{
	public ListUndischargedPatients(UserinterfaceData data,
			ConsutlPatientFileData d) {
		super(data, d);

	}

	@Override
	public Usecase Execute() {
		ConsultPatientFileController c;
		try {
			c = new ConsultPatientFileController(data.getLoginController());
			chaindata.setPatientFileOpenController(c);

			Map<String, PatientFileIN> map = new HashMap<String, PatientFileIN>();
			Collection<PatientFileIN> patientfiles = chaindata
					.getPatientfileOpenController().getActivePatientFiles();
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
				chaindata.getPatientfileOpenController().openPatientFile(
						map.get(patientName));

				data.setRegpatctrl(chaindata.getPatientfileOpenController());
				System.out.println("Patient :"
						+ chaindata.getPatientfileOpenController()
								.getPatientFile().getName()
						+ "'s file opened succesfully!");
				return new SelectUsecase(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
