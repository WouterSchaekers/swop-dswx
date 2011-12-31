package ui.prescribetreatment;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class PrescribeTreatmentSuper extends Usecase
{

	protected PrescribeTreatementData chaindata;
	public PrescribeTreatmentSuper(UserinterfaceData data) {
		this(data, new PrescribeTreatementData());
		// TODO Auto-generated constructor stub
	}
	public PrescribeTreatmentSuper(UserinterfaceData data,PrescribeTreatementData chaindata)
	{
		super(data);
		this.chaindata=chaindata;
	}

}
