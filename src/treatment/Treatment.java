package treatment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import patient.Result;

/**
 * This class is the superclass of all treatments
 */
public abstract class Treatment
{
	// a static arraylist of all treatments available.
	// TODO: is this realy what we want?
	private static ArrayList<Treatment> treatments = new ArrayList<Treatment>(
			Arrays.asList(new Cast("", 0), new Medication(null, false),
					new Surgery("")));
	private Collection<Result> results = new ArrayList<Result>();
	// all childclasses will have their names be final and static and will use
	// this var to store that information in.
	protected final String treatmentName;

	/**
	 * Default constructor.
	 * 
	 * @param treatmentName
	 *            The name of this treatment.
	 */
	public Treatment(String treatmentName) {
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
	public String getTreatmentName() {
		return treatmentName;
	}

	/**
	 * @return All available treatments as a string.
	 */
	// XXX: wa doet dit hier ? was er mis me .toString te callen in ui ? wtf
	// public String getTreatmentsAsString(){
	// String result = " ";
	// for(int i = 0; i < treatments.size(); i++){
	// result = result.concat(i + 1 + ". " +
	// treatments.get(i).getTreatmentName() + "\n");
	// }
	// return result;
	// }
	@Override
	public String toString() {
		return this.getTreatmentName();
	}

	/**
	 * Method to add a result to an existing treatment
	 */
	public void addResult(Result r) {
		this.results.add(r);
	}

	/**
	 * Method to retrieve all results for this treatment.
	 * 
	 * @return
	 */
	public Collection<Result> getAllResults() {
		return new ArrayList<Result>(results);
	}
}
