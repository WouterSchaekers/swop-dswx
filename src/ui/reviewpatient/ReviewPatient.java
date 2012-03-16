package ui.reviewpatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.ConsultPatientFileController;
import controllers.LoginController;

public class ReviewPatient extends Usecase
{

	public ReviewPatient(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		if (!validPatientFileOpen(data.getLoginController(),
				data.getPatientFileOpenController())) {
			System.out.println("You have no rights doing this.");
		}
		System.out.println("What would you like to review of patient:"
				+ data.getPatientFileOpenController().getPatientFile()
						.getName());
		System.out.println("1: review diagnoses");
		System.out.println("2: review medical tests");
		System.out.println("Q: quit");
		String in = input.nextLine();
		if (in.equalsIgnoreCase("1"))
			return new ReviewDiagnosis(data);
		if (in.equalsIgnoreCase("2"))
			return new ReviewMedicalTests(data);
		if (in.equalsIgnoreCase("Q"))
			return new SelectUsecase(data);
		System.out.println("invalid input try again");
		return this;
	}

	private boolean validPatientFileOpen(LoginController loginController,
			ConsultPatientFileController patientFileOpenController) {
		if (loginController == null || patientFileOpenController == null)
			return false;
		if (!patientFileOpenController.isValidLoginController(loginController))
			return false;
		if (patientFileOpenController.getPatientFile() == null)
			return false;
		return true;
	}

}
