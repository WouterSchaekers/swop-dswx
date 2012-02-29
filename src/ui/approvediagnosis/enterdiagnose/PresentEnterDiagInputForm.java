package ui.approvediagnosis.enterdiagnose;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import ui.approvediagnosis.ApproveDiagnosisData;
import ui.approvediagnosis.ApproveDiagnosisSuper;

public class PresentEnterDiagInputForm extends ApproveDiagnosisSuper
{

	public PresentEnterDiagInputForm(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		System.out.println("Entering new diagnose for patient"
				+ data.getPatientFileOpenController().getPatientFile()
						.getName());
		System.out.println("Please enter the diagnose !");
		System.out.println();
		System.out.println("Diagnose:");
		String in = input.nextLine();
		System.out
				.println("Input entered, are you content with this as your diagnose?");
		System.out.println(in);
		System.out.println("Yes:y No:n");
		String v = input.nextLine();
		if (v.equalsIgnoreCase("n"))
			return this;
		if (v.equalsIgnoreCase("y")) {
			chaindata.setDiagnose(in);
			return new RegisterDiagnoseInput(data, chaindata);
		}

		return new SelectUsecase(data);
	}

}
