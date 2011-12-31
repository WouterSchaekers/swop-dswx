package ui.entermedicaltestresult;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class EnterMedicalTestResultSuperClass extends Usecase
{
	
	protected EnterMedicalTestResultData chaindata;

	public EnterMedicalTestResultSuperClass(UserinterfaceData data)
	{
		this(data,new EnterMedicalTestResultData());
	}
	public EnterMedicalTestResultSuperClass(UserinterfaceData data,EnterMedicalTestResultData chaindata) {
		super(data);
		this.chaindata=chaindata;
		
	}
	

}
