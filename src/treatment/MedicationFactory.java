package treatment;

import warehouse.Warehouse;
import warehouse.item.MedicationType;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidHospitalDateException;

public class MedicationFactory extends TreatmentFactory
{
	private String description_;
	private boolean sensitive_;
	private MedicationType medicationType_;
	private Warehouse warehouse_;

	/**
	 * Default constructor. Package visible as it should only be used by
	 * Treatments.
	 */
	MedicationFactory() {
	}

	private boolean isValidDescription() {
		return !(description_ == null || description_.isEmpty());
	}

	private boolean isValidMedicationType() {
		return this.medicationType_ != null;
	}

	private boolean isValidWarehouse() {
		return this.warehouse_ != null;
	}

	public void setDescription(String description) {
		this.description_ = description;
	}

	public void setSensitive(boolean sensitive) {
		this.sensitive_ = sensitive;
	}

	public void setMedicationType(MedicationType medicationType) {
		this.medicationType_ = medicationType;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse_ = warehouse;
	}

	@Override
	protected boolean isReady() {
		return super.isReady() && isValidDescription() && isValidMedicationType() && isValidWarehouse();
	}

	@Override
	public Treatment create() throws FactoryInstantiationException,	InvalidHospitalDateException {
		if (!isReady())
			throw new FactoryInstantiationException("Medication was not ready yet!");
		return new Medication(this.patientFile_, this.creationDate_, this.medicationType_, this.warehouse_,
					this.description_, this.sensitive_);
	}
}