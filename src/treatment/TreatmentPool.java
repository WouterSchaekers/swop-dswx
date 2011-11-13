package treatment;

import java.util.Collection;

/**
 * This class is merely a collection of all treatments in existence.
 */
public class TreatmentPool
{
	private Collection<Treatment> allTreatments;

	public void addTreatment(Treatment m) {
		allTreatments.add(m);
	}

	public void removeTreatment(Treatment m) {
		allTreatments.remove(m);
	}
	
	public Collection<Treatment> getAllTreatments() {
		return this.allTreatments;
	}
}
