package ui.approvediagnosis;

import java.util.Collection;
import controllers.interfaces.DiagnoseIN;
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
	public Usecase Execute() {
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
			chaindata.getController().approveDiagnose(data.getLoginController(),selected);
			return null;
		}
		if(inString.equals("n"))
		{
			chaindata.getController().disApproveDiagnose(data.getLoginController(),selected);
			return null;
		}
		System.out.println("Wrong input, please try again");
		chaindata.getAllTheDiagnosis().add(selected);
		return this;
	}

}
