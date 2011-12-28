package ui.approvediagnosis;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class ApproveDiagnosisSuper extends Usecase
{

	protected ApproveDiagnosisData chaindata;

	private ApproveDiagnosisSuper(UserinterfaceData data, ApproveDiagnosisData chaindata) {
		super(data);
		this.chaindata=chaindata;
	}
	public ApproveDiagnosisSuper(UserinterfaceData data)
	{
		this(data,new ApproveDiagnosisData());
	}
}
