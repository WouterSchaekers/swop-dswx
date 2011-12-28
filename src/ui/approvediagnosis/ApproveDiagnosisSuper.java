package ui.approvediagnosis;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class ApproveDiagnosisSuper extends Usecase
{

	protected ApproveDiagnosisData chaindata;

	protected ApproveDiagnosisSuper(UserinterfaceData data, ApproveDiagnosisData chaindata) {
		super(data);
		this.chaindata=chaindata;
	}
	protected ApproveDiagnosisSuper(UserinterfaceData data)
	{
		this(data,new ApproveDiagnosisData());
	}
}
