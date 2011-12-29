package ui.approvediagnosis;

import java.util.Iterator;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.TreatmentIN;
import ui.Usecase;
import ui.UserinterfaceData;

public class DisplayScheduledTreatment extends ApproveDiagnosisSuper
{

	protected DisplayScheduledTreatment(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		System.out.println("Scheduled treatment :");
		DiagnoseIN diagnose = chaindata.getDiagnose();
		diagnose.getTreatments();
		for(TreatmentIN treatment: diagnose.getTreatments())
			System.out.println(treatment);
		return null;
	}

}
