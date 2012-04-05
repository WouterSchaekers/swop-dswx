package ui.usecases;

import help.Collections;
import help.Filter;
import java.util.Collection;
import controllers.EvaluateDiagnoseController;
import controllers.interfaces.DiagnoseIN;
import exceptions.ApproveDiagnoseException;
import exceptions.DischargePatientException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;

public class ReviewPendingDiagnoses extends UseCase
{
	private Displayer<DiagnoseIN> diagnose = new Displayer<DiagnoseIN>()
	{

		@Override
		public void display(DiagnoseIN t) {
			printLn("Diagnose :"+t.getDiagnoseIN());
			printLn("Complaints:"+t.getComplaintsIN());
			print("By :"+t.getAttendingIN().getName());
			
			
		}
	};
	private EvaluateDiagnoseController controller;

	public ReviewPendingDiagnoses(UIData data) throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController, DischargePatientException {
		super(data, 12);
		controller = new EvaluateDiagnoseController(data.getLoginController(), data.getConsultPatientFileopenController());
	}

	@Override
	public UseCase execute() {
		printLn("Reviewing diagnoses:");
		printLn("Select one");
		Collection<DiagnoseIN> diagnoses= controller.getPendingDiagnosis();
		diagnoses = filterSecondOp(diagnoses);
		Selector<DiagnoseIN> diagnoseSelector = new Selector<DiagnoseIN>(diagnoses, diagnose);
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
			if(selected.isApprovedIN())
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
			diagnose.display(diag);
			return mm();
		}
	}

	private Collection<DiagnoseIN> filterSecondOp(Collection<DiagnoseIN> diagnoses) {
		
		return Collections.filter(diagnoses,new Filter(){
			@Override
			public <T> boolean allows(T arg) {
				return ((DiagnoseIN)arg).needsSecOpFromIN()!=null;
			}
			
		});
	}
	@Override
	public String toString()
	{
		return "Review my second-opinion diagnoses";
	}
}