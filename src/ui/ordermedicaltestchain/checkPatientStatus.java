package ui.ordermedicaltestchain;

import controllers.DTOPatientFile;
import controllers.PatientFileOpenController;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.usecase;

public class checkPatientStatus extends MedicalTestCommand
{

	public checkPatientStatus(UserinterfaceData data, MedicalTestData medData) {
		super(data, medData);
	}

	@Override
	public usecase Execute() {
		PatientFileOpenController pfoc = data.getPatientFileOpenController();
		DTOPatientFile pf = pfoc.getPatientFile();
		if(!pf.isDischarged())
			return new listTreatments(data,medData);
		return new SelectUsecase(data);
	}

}
