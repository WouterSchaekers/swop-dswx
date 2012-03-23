package treatment;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class MedicationFactory extends TreatmentFactory
{
	private String description_;
	private boolean sensitive_;
	
	/**
	 * Default constructor. Package visible as it should only be used by Treatments.
	 */
	MedicationFactory() {}
	
	public void setDescription(String description) {
		this.description_ = description;
	}
	
	private boolean isValidDescription() {
		return !(description_ == null || description_.isEmpty());
	}
	
	public void setSensitive(boolean sensitive) {
		this.sensitive_ = sensitive;
	}
	
	protected boolean isReady() {
		return super.isReady() && isValidDescription();
	}
	
	@Override
	public Treatment create() throws FactoryInstantiationException, InvalidAmountException, InvalidHospitalDateException {
		if(isReady())
			return new Medication(patientFile_, creationDate_, description_, sensitive_);
		throw new FactoryInstantiationException("Medication was not ready yet!");
	}

}
