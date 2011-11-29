package ui.ordermedicaltestchain;

import controllers.LoginController;
import controllers.interfaces.UserIN;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;

public class PatientFileOpenChecker extends MedicalTestCommand
{

	public PatientFileOpenChecker(UserinterfaceData data,
			MedicalTestData medData) {
		super(data, medData);
	}

	@Override
	public Usecase Execute() {
		// check if the doctor has the patient file opened at this moment.
		LoginController lc = data.getLoginController();
		UserIN docLc = lc.getUserIN();
		UserIN curDoc = data.getPatientFileOpenController().getDocIN();

		if (docLc.equals(curDoc))
			return new CheckPatientStatus(data, medData);
		return new SelectUsecase(data);

	}

}
