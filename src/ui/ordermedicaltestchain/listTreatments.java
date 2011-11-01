package ui.ordermedicaltestchain;

import ui.UserinterfaceData;
import ui.usecase;

public class listTreatments extends MedicalTestCommand
{

	public listTreatments(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public usecase Execute() {
		System.out.println("Possible treatments are:\n");
		System.out.println(medData.types);
		return new selectMedicalTest(data,medData);
	}

}
