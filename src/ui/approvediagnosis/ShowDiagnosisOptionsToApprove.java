package ui.approvediagnosis;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.ApproveDiagnoseController;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidLoginControllerException;

public class ShowDiagnosisOptionsToApprove extends ApproveDiagnosisSuper
{

	protected ShowDiagnosisOptionsToApprove(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		ApproveDiagnoseController c = chaindata.getController();
		Collection<PatientFileIN> patientfiles;
		//TODO FIX THIS
		patientfiles = null;
		try {
			patientfiles = c.getAllPatienFiles(null);
		} catch (InvalidLoginControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<DiagnoseIN> d = new ArrayList<DiagnoseIN>();
		for (PatientFileIN pf : patientfiles)
			d.addAll(pf.getAlldiagnosis());
		d = Collections.filter(d, new Filter()
		{

			@Override
			public <T> boolean allows(T arg) {
				DiagnoseIN d = (DiagnoseIN) arg;
				return !d.isApproved()
						&& d.needsSecOpFrom().equals(
								ShowDiagnosisOptionsToApprove.this.data
										.getLoginController().getUserIN());
			}
		});
		chaindata.setUnapprovedDiagnoses(d);
		return new SelectDiagnosis(data, chaindata);
	}

}
