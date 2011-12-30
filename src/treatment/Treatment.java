package treatment;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidResultException;
import be.kuleuven.cs.som.annotate.Basic;
import result.Result;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment implements TreatmentIN
{

	private Collection<Result> results = new ArrayList<Result>();
	private long duration;
	/**
	 * Default constructor.
	 * 
	 * @param treatmentName
	 *            The name of this treatment.
	 */
	public Treatment(long duration) {
		this.duration=duration;
	}
	
	public long getDuration(){
		return duration;
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
	
	public abstract void setResult(Result r);
}
