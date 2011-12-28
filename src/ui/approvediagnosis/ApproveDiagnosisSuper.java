package ui.approvediagnosis;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class ApproveDiagnosisSuper extends Usecase
{

	protected ApproveDiagnosisData chaindata;

	public ApproveDiagnosisSuper(UserinterfaceData data, ApproveDiagnosisData chaindata) {
		super(data);
		this.chaindata=chaindata;
		// TODO Auto-generated constructor stub
	}

}
