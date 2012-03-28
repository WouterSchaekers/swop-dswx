package ui.reviewpatient;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.TreatmentIN;

public class ReviewDiagInDetail extends Usecase
{
	DiagnoseIN diag;

	public ReviewDiagInDetail(UserinterfaceData data, DiagnoseIN diag) {
		super(data);
		this.diag = diag;
	}

	@Override
	public Usecase Execute() {
		System.out.println("Diagnose for "
				+ data.getPatientFileOpenController().getPatient().getName()
				+ " selected.");
		System.out.println("By Doctor :" + diag.getAttending().getName());
		System.out.println();
		System.out.println("Diagnose details: " + diag.getDiagnosis());
		Collection<TreatmentIN> treatments = diag.getTreatments();
		if (treatments.isEmpty()) {
			System.out.println("No treatment for this diagnose");
			return new ReviewMore(data);
		}
		int i = 0;
		Map<Integer, TreatmentIN> treats = new HashMap<Integer, TreatmentIN>();
		for (TreatmentIN d : treatments) {
			treats.put(i, d);
			System.out.println("Treatment :" + d);
			i++;
		}
		int menuoption = -1;
		do {
			String in = input.nextLine();
			try {
				menuoption = new Integer(in);
			} catch (Exception e) {
				System.out.println("Not valid input");
			}
		} while (!treats.containsKey(menuoption));
		return new ReviewMore(data);
	}

}
