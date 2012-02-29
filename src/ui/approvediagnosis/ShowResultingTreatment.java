package ui.approvediagnosis;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.interfaces.TreatmentIN;

public class ShowResultingTreatment extends ApproveDiagnosisSuper
{

	public ShowResultingTreatment(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		System.out.println("Resulting treatment :");
		for (TreatmentIN treatment : this.chaindata.getDiagnose()
				.getTreatments())
			System.out.println(treatment);
		return new SelectUsecase(data);
	}

}
