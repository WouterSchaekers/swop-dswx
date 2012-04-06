package ui.usecases;

import java.util.Collection;
import ui.UIData;
import ui.UseCase;
import controllers.EvaluateDiagnoseController;
import controllers.interfaces.DiagnoseIN;
import exceptions.ApproveDiagnoseException;
import exceptions.DischargePatientException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class ReviewPendingDiagnoses extends UseCase
{
	private EvaluateDiagnoseController controller;

	public ReviewPendingDiagnoses(UIData data) throws InvalidLoginControllerException, InvalidHospitalException, InvalidConsultPatientFileController, DischargePatientException {
		super(data, 12);
		controller = new EvaluateDiagnoseController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		printLn("Reviewing diagnoses:");
		
		Collection<DiagnoseIN> diagnoses= controller.getPendingDiagnosis();
		if(diagnoses.isEmpty()){
			printLn("No diagnoses.");
			return mm();
		}
		printLn("Select one");
		Selector<DiagnoseIN> diagnoseSelector = new Selector<DiagnoseIN>(diagnoses, Selector.diagnose);
		DiagnoseIN selected =diagnoseSelector.get();
		printLn("Approve diagonse.");
		boolean approve = Selector.yesNoSelector.get();
		if(approve)
		{
			try {
				controller.approveDiagnose(selected);
			} catch (ApproveDiagnoseException e) {
				printLn("");
				return mm();
			}
			if(selected.isApproved())
				printLn("Diagnose is now approved.");
			else
				printLn("Something went wrong, diagnose is not yet approved.");
			return mm();
		}else
		{
			//disapprove
			print("Give the new diagnose:");
			String newDiag = read();
			DiagnoseIN diag;
			try {
				diag = controller.disapproveDiagnose(selected, newDiag);
			} catch (ApproveDiagnoseException e) {
				printLn("Diagnose cannot be disapproved.");
				return mm();
			}
			printLn("Diagnose is sucesfully disaproved and replaced with :");
			Selector.diagnose.display(diag);
			return mm();
		}
	}


	@Override
	public String toString()
	{
		return "Review my second-opinion diagnoses";
	}
}