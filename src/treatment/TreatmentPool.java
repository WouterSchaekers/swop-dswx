package treatment;

import java.util.ArrayList;
import java.util.Collection;
import exceptions.InvalidTreatmentException;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class is merely a collection of all treatments in existence.
 */
public class TreatmentPool
{
	private Collection<Treatment> allTreatments;
	
	/**
	 * This method will add a Treatment to this TreatmentPool.
	 * @param m
	 * The treatment to add.
	 * @throws InvalidTreatmentException 
	 */
	public void addTreatment(Treatment m) throws InvalidTreatmentException {
		if(!isValidTreatment(m))
			throw new InvalidTreatmentException("Invalid Treatment in addTreatment in TreatmentPool");
		allTreatments.add(m);
	}

	/**
	 * This method will remove a Treatment from this TreatmentPool
	 * @param m
	 */
	public void removeTreatment(Treatment m) {
		allTreatments.remove(m);
	}

	@Basic
	public Collection<Treatment> getAllTreatments() {
		return new ArrayList<Treatment>(this.allTreatments);
	}

	/**
	 * @return True if m is a valid treatment for this pool.
	 */
	private boolean isValidTreatment(Treatment m) {
		return m != null;
	}
}
