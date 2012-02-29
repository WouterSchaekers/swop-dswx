package ui.ordermedicaltest;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class OrderMedicalTestSuperClass extends Usecase
{
	protected MedicalTestData chaindata;

	public OrderMedicalTestSuperClass(UserinterfaceData data) {
		this(data, new MedicalTestData());
	}

	@Override
	public abstract Usecase Execute();

	public OrderMedicalTestSuperClass(UserinterfaceData uiData,
			MedicalTestData medData) {
		super(uiData);
		this.chaindata = medData;
	}

}
