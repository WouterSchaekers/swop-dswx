package ui.reviewpatient;

import controllers.interfaces.MedicalTestIN;
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
		System.out.println("Medical tests for:"+pf.getName());
		for(MedicalTestIN test:pf.getallMedicalTests())
			{
			System.out.println(test.appointmentInfo());
			}
			System.out.println("In dept reviewing is not yet enabled.");
		return new ReviewMore(data);
	}

}
