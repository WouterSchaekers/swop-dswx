package ui.ordermedicaltestchain;

import controllers.DTOUser;
import controllers.LoginController;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.usecase;

public class PatientFileOpenChecker extends MedicalTestCommand
{

	public PatientFileOpenChecker(UserinterfaceData data, MedicalTestData medData) {
		super(data, medData);
	}

	@Override
	public usecase Execute() {
		LoginController lc = data.getLoginController();
		DTOUser docLc = lc.getUserDTO();
		DTOUser curDoc = data.getPatientFileOpenController().getDocDTO();
		
		if(docLc.equals(curDoc))
			return new checkPatientStatus(data,medData);
		return new SelectUsecase(data);
		
	}

}
