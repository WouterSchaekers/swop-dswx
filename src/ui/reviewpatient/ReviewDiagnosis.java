package ui.reviewpatient;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;
import ui.Usecase;
import ui.UserinterfaceData;

public class ReviewDiagnosis extends Usecase
{

	public ReviewDiagnosis(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		//sort diagnosis on date and show the most recent ones first
		PatientFileIN pf=	this.data.getPatientFileOpenController().getPatientFile();
		Collection<DiagnoseIN> diagnoses=pf.getAlldiagnosis();
		if(diagnoses.isEmpty())
		{
			System.out.println("There are no diagnoses for this patient.");
			return new ReviewMore(data);
		}
		int i = 0;
		Map<Integer,DiagnoseIN> diags = new HashMap<Integer, DiagnoseIN>();
		for(DiagnoseIN d : diagnoses)
		{
			diags.put(i, d);
			System.out.println("Diagnose by "+d.getAttending().getName());
			System.out.println(i+" :\t\t" + d.getDiagnosis() );
			System.out.println();
			i++;
		}
		int menuoption =-1;
		do{
		String in = input.nextLine();
		try{
		menuoption=new Integer(in);
		}catch(Exception e){
			System.out.println("Not valid input");
		}
		}while(!diags.containsKey(menuoption));
		
		return new ReviewDiagInDetail(data,diags.get(menuoption));
	}

}
