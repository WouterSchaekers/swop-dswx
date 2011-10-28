package treatment;

import java.util.ArrayList;
import java.util.Collection;


public abstract class Treatment
{
	private static ArrayList<Treatment> treatments = new ArrayList<Treatment>();
	
	private final String treatmentName;
	private int duration = 0;
	public static Collection<Treatment> availableTreatments() {
		Cast cast = new Cast("", 0);
		Medication medication = new Medication(null, false);
		Surgery surgery = new Surgery("");
		treatments.add(cast);
		treatments.add(medication);
		treatments.add(surgery);
		return treatments;
	}
	public Treatment(String treatmentName){
		this.treatmentName = treatmentName;
	}
	
	public String getTreatmentName(){
		return treatmentName;
	}
	
	public String getTreatments(){
		String result =  " ";
		for(int i = 0; i < treatments.size(); i++){
			result = result.concat(i + 1 + ". " + treatments.get(i).getTreatmentName() + "\n");
		}
		return result;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}
}
