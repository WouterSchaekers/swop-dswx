package treatment;

import java.util.ArrayList;
import java.util.Collection;
import exceptions.InvalidResultException;
import be.kuleuven.cs.som.annotate.Basic;
import patient.Result;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment
{

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

	@Basic
	public String getTreatmentName() {
		return treatmentName;
	}

	@Override
	public String toString() {
		return this.getTreatmentName();
	}

	/**
	 * Method to add a result to an existing treatment
	 * @throws InvalidResultException 
	 * 
	 */
	public void addResult(Result r) throws InvalidResultException {
		if(!isValidResult(r))
			throw new InvalidResultException("Invalid Result in addResult from Treatment");
		this.results.add(r);
	}
	
	/**
	 * @return True if r is a valid Result.
	 */
	private boolean isValidResult(Result r) {
		return r != null;
	}

	@Basic
	public Collection<Result> getAllResults() {
		return new ArrayList<Result>(results);
	}
}
