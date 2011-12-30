package ui.entermedicaltestresult;

import ui.Usecase;
import ui.UserinterfaceData;

public class EnterMedicalTestResultSuperClass extends Usecase
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
	

	@Override
	public Usecase Execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
