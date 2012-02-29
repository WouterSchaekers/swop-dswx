package ui.enterdiagnosis;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class EnterDiagnoseSuperClass extends Usecase
{

	protected EnterDiagnoseData chaindata;

	EnterDiagnoseSuperClass(UserinterfaceData data, EnterDiagnoseData chaindata) {
		super(data);
		this.chaindata = chaindata;
	}

	public EnterDiagnoseSuperClass(UserinterfaceData data) {
		this(data, new EnterDiagnoseData());
	}

}
