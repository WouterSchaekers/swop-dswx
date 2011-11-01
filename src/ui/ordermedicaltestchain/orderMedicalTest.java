package ui.ordermedicaltestchain;

import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.usecase;
import users.User.usertype;

public class orderMedicalTest extends MedicalTestCommand
{

	public orderMedicalTest(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		if (data.getLoginController().getUserDTO().type() == usertype.Doctor) {
			return new PatientFileOpenChecker(data);
		} else {
			System.out.println(data.getLoginController().getUserDTO().getName()
					+ " is not a doctor in this hospital");
			return new SelectUsecase(data);
		}
	}

}
