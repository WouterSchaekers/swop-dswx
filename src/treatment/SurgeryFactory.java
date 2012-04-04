package treatment;

import exceptions.FactoryInstantiationException;

public class SurgeryFactory extends TreatmentFactory
{
	private String description_;

	/**
	 * Default constructor. Package visible as it should only be used by
	 * Treatments.
	 */
	SurgeryFactory() {
	}

	public void setDescription(String description) {
		this.description_ = description;
	}

	private boolean isValidDescription() {
		return !(description_ == null || description_.isEmpty());
	}

	protected boolean isReady() {
		return super.isReady() && isValidDescription();
	}

	@Override
	public Treatment create() throws FactoryInstantiationException{
		if (isReady())
			return new Surgery(patientFile_, creationDate_, description_);
		throw new FactoryInstantiationException("Surgery was not ready yet!");
	}

}
