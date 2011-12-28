package ui.approvediagnosis;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import patient.Diagnose;
import controllers.ApproveDiagnosisController;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;
import ui.Usecase;
import ui.UserinterfaceData;

public class ShowDiagnosisOptionsToApprove extends ApproveDiagnosisSuper
{

	protected ShowDiagnosisOptionsToApprove(UserinterfaceData data, ApproveDiagnosisData chaindata) {
		super(data,chaindata);
	}

	@Override
	public Usecase Execute() {
		ApproveDiagnosisController c =chaindata.getController();
		Collection<PatientFileIN> patienfiles=c.getAllPatienFiles(data.getDataPasser());

		Collection<DiagnoseIN> d = new ArrayList<DiagnoseIN>();
		for(PatientFileIN pf:patienfiles)
			d.addAll(pf.getAlldiagnosis());
		d=Collections.filter(d, new Filter()
		{
			
			@Override
			public <T> boolean allows(T arg) {
				DiagnoseIN d= (DiagnoseIN)arg;
				return d.isApproved()&&d.needsSecOpFrom().equals(ShowDiagnosisOptionsToApprove.this.data.getLoginController().getUserIN());
			}
		});
		chaindata.setUnapprovedDiagnoses(d);
		return new SelectDiagnosis(data,chaindata);
	}

}
