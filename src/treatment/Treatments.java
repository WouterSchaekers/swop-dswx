package treatment;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Holds on to all the treatment factories.
 */
public class Treatments
{
	public static Collection<TreatmentFactory> factories() {
		Collection<TreatmentFactory> treatments = new LinkedList<TreatmentFactory>();
		treatments.clear();
		treatments.add(new CastFactory());
		treatments.add(new SurgeryFactory());
		treatments.add(new MedicationFactory());
		return new LinkedList<TreatmentFactory>(treatments);
	}
}
