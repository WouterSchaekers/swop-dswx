package treatment;

import warehouse.Warehouse;
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
	private Warehouse warehouse_;

	/**
	 * Default constructor.
	 */
	MedicationFactory() {
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
	 * Sets the warehouse of the factory.
	 * 
	 * @param warehouse
	 *            The warehouse.
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse_ = warehouse;
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
	 * Checks whether the warehouse is valid.
	 * 
	 * @return True if the warehouse is not null.
	 */
	private boolean isValidWarehouse() {
		return this.warehouse_ != null;
	}

	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the description, the medicationType and the warehouse is
	 *         valid.
	 */
	@Override
	protected boolean isReady() {
		return super.isReady() && isValidDescription() && isValidMedicationType() && isValidWarehouse();
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
		return new Medication(this.patientFile_, this.creationDate_, this.medicationType_, this.warehouse_,
				this.description_, this.sensitive_);
	}
}