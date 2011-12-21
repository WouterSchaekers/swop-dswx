package ui.openpatientfile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import controllers.PatientFileOpenController;
import controllers.interfaces.PatientFileIN;
import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.Usecase;
import users.Doctor;

public class ListUndischargedPatients extends Usecase
{
	UserinterfaceData data;
	ConsutlPatientFileData patfiledata;

	public ListUndischargedPatients(UserinterfaceData data,
			ConsutlPatientFileData d) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		if (!(data.getLoginController().getUserIN() instanceof Doctor)) {
			System.out.println(data.getLoginController().getUserIN().getName()
					+ " is not a Doctor");
			return new SelectUsecase(data);

		}
		patfiledata.setPatientFileOpenController(new PatientFileOpenController(
				data.getDataPasser(), data.getLoginController()));
		Map<String, PatientFileIN> map = new HashMap<String, PatientFileIN>();
		Collection<PatientFileIN> patientfiles = patfiledata
				.getPatientfileOpenController().getAllPatientFiles(
						data.getLoginController());
		for (PatientFileIN file : patientfiles) {
			map.put(file.getName(), file);
			System.out.println(file.getName());
		}
		// String name = input.nextLine();
		// TODO fix
		return new SelectUsecase(data);
	}

}
