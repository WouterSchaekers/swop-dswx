package ui.prescribetreatment;

import ui.Usecase;
import ui.UserinterfaceData;

public class ListAvailableTreatments extends PrescribeTreatmentSuper
{

	public ListAvailableTreatments(UserinterfaceData data,
			PrescribeTreatementData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		// chaindata.getPrescribeTreatmentController().getAllAvailableTreatments(data.getLoginController(),data.getPatientFileOpenController());
		System.out.println("");
		return null;
	}

}
