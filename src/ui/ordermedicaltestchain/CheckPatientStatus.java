package ui.ordermedicaltestchain;

import controllers.DTOPatientFile;
import controllers.PatientFileOpenController;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;

public class CheckPatientStatus extends MedicalTestCommand
{

	public CheckPatientStatus(UserinterfaceData data, MedicalTestData medData) {
		super(data, medData);
	}

	@Override
	public Usecase Execute() {
		// check if the patient is not discharged at this moment.
		PatientFileOpenController pfoc = data.getPatientFileOpenController();
		DTOPatientFile pf = pfoc.getPatientFile();
		if(!pf.isDischarged())
			return new ListTreatments(data,medData);
		return new SelectUsecase(data);
	}

}
