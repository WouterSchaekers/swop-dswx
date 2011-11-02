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
		// list all treatments and continue
		System.out.println("Possible treatments are:\n");
		System.out.println(medData.types);
		return new selectMedicalTest(data,medData);
	}

}
