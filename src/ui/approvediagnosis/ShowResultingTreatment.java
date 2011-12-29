package ui.approvediagnosis;

import controllers.interfaces.TreatmentIN;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class ShowResultingTreatment extends ApproveDiagnosisSuper
{

	public ShowResultingTreatment(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		System.out.println("Resulting treatment :");
		for(TreatmentIN treatment : this.chaindata.getDiagnose().getTreatments())
			System.out.println(treatment);
		return new SelectUsecase(data);
	}

}
