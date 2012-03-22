package ui.reviewpatient;

import ui.Usecase;
import ui.UserinterfaceData;
import controllers.interfaces.PatientFileIN;

public class ReviewMedicalTests extends Usecase
{

	public ReviewMedicalTests(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		PatientFileIN pf = data.getPatientFileOpenController().getPatientFile();
		System.out.println("Medical tests for:" + pf.getName());
		// for (MedicalTestIN test : pf.getallMedicalTests()) {
		System.out.println("Really cool appointment info!");
		// TODO: fix
		// System.out.println(test.appointmentInfo());
		// }
		System.out.println("In dept reviewing is not yet enabled.");
		return new ReviewMore(data);
	}

}
