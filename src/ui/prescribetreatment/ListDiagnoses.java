package ui.prescribetreatment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.TreatmentIN;

public class ListDiagnoses extends PrescribeTreatmentSuper
{

	public ListDiagnoses(UserinterfaceData data,
			PrescribeTreatementData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		int menuoptions = 0;
		Map<Integer, DiagnoseIN> menu = new HashMap<Integer, DiagnoseIN>();
		for (DiagnoseIN d : data.getPatientFileOpenController()
				.getPatientFile().getAlldiagnosis()) {
			menu.put(menuoptions++, d);
			System.out.println(menuoptions - 1 + "Diagnose :");
			System.out.println(d.getDiagnosis()
					+ " for "
					+ data.getPatientFileOpenController().getPatientFile()
							.getName());
			System.out.println("By :" + d.getAttending());
			Collection<TreatmentIN> treats = d.getTreatments();
			if (treats.isEmpty())
				continue;
			System.out.println("Treatments belonging to this diagnose: ");
			int i = 0;
			for (TreatmentIN treatment : treats)
				System.out.println("\t\tTreatment:" + i + treatment);
		}
		String in = input.nextLine();
		try {
			menuoptions = new Integer(in);
		} catch (Exception e) {
			System.out.println("Invalid menu option try again!");
			return this;
		}
		if (!menu.containsKey(menuoptions)) {
			System.out.println("invalid menu option!");
			return this;
		}
		chaindata.setDiagnoseToTreat(menu.get(menuoptions));
		return new ListAvailableTreatments(data, chaindata);
	}

}
