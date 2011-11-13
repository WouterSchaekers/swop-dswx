package treatment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * This class is the superclass of all treatments
 */
public abstract class Treatment 
{
	// a static arraylist of all treatments available.
	private static ArrayList<Treatment> treatments = new ArrayList<Treatment>(Arrays.asList(new Cast("", 0), new Medication(null, false), new Surgery("")));
	// all childclasses will have their names be final and static and will use this var to store that information in.
	protected final String treatmentName;
	protected int duration = 0; // the duration of the treatment 
	
	/**
	 * Default constructor. 
	 * @param treatmentName
	 * The name of this treatment.
	 */
	public Treatment(String treatmentName, int duration){
		this.treatmentName = treatmentName;
	}
	
	/**
	 * @return All available treaments.
	 */
	public static Collection<Treatment> availableTreatments() {
		return treatments;
	}
	
	/** 
	 * @return the name of this treatment.
	 */
	public String getTreatmentName(){
		return treatmentName;
	}
	
	/**
	 * @return All available treatments as a string.
	 */
	public String getTreatmentsAsString(){
		String result =  " ";
		for(int i = 0; i < treatments.size(); i++){
			result = result.concat(i + 1 + ". " + treatments.get(i).getTreatmentName() + "\n");
		}
		return result;
	}
	
	/**
	 * @return The duration of this treatment.
	 */
	public int getDuration(){
		return duration;
	}
	
	/**
	 * This method allows to change the duration of this treatment.
	 * @param duration
	 * The new duration of this treatment.
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	

	
}
