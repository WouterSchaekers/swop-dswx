package ui.reviewpatient;

import controllers.interfaces.PatientFileIN;
import ui.Usecase;
import ui.UserinterfaceData;

public class ReviewMedicalTests extends Usecase
{

	public ReviewMedicalTests(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		PatientFileIN pf = data.getPatientFileOpenController().getPatientFile();
		System.out.println("Medical tests not yet implemented sorry QQ");
		return new ReviewMore(data);
	}

}
