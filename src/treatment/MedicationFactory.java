package treatment;

import warehouse.item.MedicationType;
import exceptions.FactoryInstantiationException;

/**
 * A MedicationFactory is a factory, used to create a Medication.
 */
public class MedicationFactory extends TreatmentFactory
{
	private String description_;
	private boolean sensitive_;
	private MedicationType medicationType_;

	/**
	 * Default constructor.
	 */
	public MedicationFactory() {
		;
	}

	/**
	 * Sets the description of the factory.
	 * 
	 * @param description
	 *            The description.
	 */
	public void setDescription(String description) {
		this.description_ = description;
	}

	/**
	 * Sets the sensitivity of the factory.
	 * 
	 * @param sensitive
	 *            The sensitivity.
	 */
	public void setSensitive(boolean sensitive) {
		this.sensitive_ = sensitive;
	}

	/**
	 * Sets the medicationType of the factory.
	 * 
	 * @param medicationType
	 *            The medicationType.
	 */
	public void setMedicationType(MedicationType medicationType) {
		this.medicationType_ = medicationType;
	}

	/**
	 * Checks whether the description is valid.
	 * 
	 * @return True if the description is not null .
	 */
	private boolean isValidDescription() {
		return this.description_ != null && !this.description_.isEmpty();
	}

	/**
	 * Checks whether the medicationType is valid.
	 * 
	 * @return True if the medicationType is not null.
	 */
	private boolean isValidMedicationType() {
		return this.medicationType_ != null;
	}

	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the description, the medicationType and the warehouse is
	 *         valid.
	 */
	@Override
	protected boolean isReady() {
		return super.isReady() && isValidDescription() && isValidMedicationType();
	}

	/**
	 * Creates a Medication built from the given information.
	 * 
	 * @return A Treatment built from the given information.
	 * @throws FactoryInstantiationException
	 *             The factory was not ready yet.
	 */
	@Override
	public Treatment create() throws FactoryInstantiationException {
		if (!isReady())
			throw new FactoryInstantiationException("Medication was not ready yet!");
		return new Medication(this.getPatientFile(), this.creationDate_, this.medicationType_, this.warehouse_,
				this.description_, this.sensitive_, diagnose_);
	}

	/**
	 * Returns a new instance of the current factory.
	 * 
	 * @return A new instance of the current factory.
	 */
	@Override
	public TreatmentFactory newInstance() {
		return new MedicationFactory();
	}
}