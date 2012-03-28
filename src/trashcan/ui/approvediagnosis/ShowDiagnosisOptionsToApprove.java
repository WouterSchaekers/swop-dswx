package ui.approvediagnosis;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.ApproveDiagnoseController;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;

public class ShowDiagnosisOptionsToApprove extends ApproveDiagnosisSuper
{

	protected ShowDiagnosisOptionsToApprove(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		try {
			ApproveDiagnoseController c = chaindata.getController();
			Collection<PatientFileIN> patientfiles;
			// TODO FIX THIS
			patientfiles = null;
			patientfiles = c.getAllPatienFiles();

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
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
