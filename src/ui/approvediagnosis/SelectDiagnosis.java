package ui.approvediagnosis;

import java.util.Collection;
import controllers.interfaces.DiagnoseIN;
import exceptions.ApproveDiagnoseException;
import exceptions.InvalidLoginControllerException;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class SelectDiagnosis extends ApproveDiagnosisSuper
{

	protected SelectDiagnosis(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute()  {
		if(chaindata.getAllTheDiagnosis().isEmpty()){
			System.out.println("You have no diagnosis awaiting your second opinion!");
		return new SelectUsecase(data);
		}
		
		DiagnoseIN selected = null;
		for(DiagnoseIN d:chaindata.getAllTheDiagnosis())
			selected =d;
		Collection<DiagnoseIN> t =chaindata.getAllTheDiagnosis();
		t.remove(selected);
		chaindata.setUnapprovedDiagnoses(t);
		System.out.println("Diagnosis to approve:" +selected.getDiagnosis());
		System.out.println("Do you approve?");
		System.out.println("Q: quit, Y: yes ,N: No");
		String inString = input.nextLine();
		if(inString.equalsIgnoreCase("Q"))
			return new SelectUsecase(data);
		if(inString.equalsIgnoreCase("y"))
		{
			DiagnoseIN diagnose=null;
			try {
				diagnose=chaindata.getController().approveDiagnose(data.getLoginController(),selected);
			} catch (InvalidLoginControllerException e) {
				System.out.println("you are not allowed to do that sorry");
				return new SelectUsecase(data);
			} catch (ApproveDiagnoseException e) {
				System.out.println("Diagnose was not approved, something went wrong");
				return new SelectUsecase(data);
			}
			System.out.println("Diagnose succesfully approved !");
			System.out.println(" ");
			chaindata.setCreatedDiagnose(diagnose);
			return new DisplayScheduledTreatment(data,chaindata);
		}
		if(inString.equals("n"))
		{
			return new EnterNewDiagnose(data,chaindata);
		}
		System.out.println("Wrong input, please try again");
		chaindata.getAllTheDiagnosis().add(selected);
		return this;
	}

}
