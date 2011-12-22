package ui.ordermedicaltestchain;

import ui.UserinterfaceData;
import ui.Usecase;

public class ListTreatments extends MedicalTestCommand
{

	public ListTreatments(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		// TODO: fix
		// list all treatments and continue
		System.out.println("Possible treatments are:\n");
		// System.out.println(medData.types);
		return new SelectMedicalTest(data, medData);
	}

}
