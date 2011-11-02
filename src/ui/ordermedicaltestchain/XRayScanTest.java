package ui.ordermedicaltestchain;

import ui.UserinterfaceData;
import ui.usecase;

public class XRayScanTest extends MedicalTestCommand
{

	public XRayScanTest(UserinterfaceData data, MedicalTestData medData) {
		super(data,medData);
	}

	@Override
	public usecase Execute() {
		System.out.println("");
		return null;
	}

}
