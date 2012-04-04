package treatment;

import exceptions.FactoryInstantiationException;

/**
 * A SurgeryFactory is a factory, used to create a surgery.
 */
public class SurgeryFactory extends TreatmentFactory
{
	private String description_;

	/**
	 * Default constructor. Package visible since it should only be used by
	 * Treatments.
	 */
	SurgeryFactory() {
		;
	}

	/**
	 * Sets the description of the surgery.
	 * 
	 * @param description
	 *            The description of the surgery.
	 */
	public void setDescription(String description) {
		this.description_ = description;
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
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the description is valid.
	 */
	@Override
	protected boolean isReady() {
		return super.isReady() && isValidDescription();
	}

	/**
	 * Creates a Surgery built from the given information.
	 * 
	 * @return A Surgery built from the given information.
	 * @throws FactoryInstantiationException
	 *             The factory was not ready yet.
	 */
	@Override
	public Treatment create() throws FactoryInstantiationException {
		if (isReady())
			return new Surgery(patientFile_, creationDate_, description_);
		throw new FactoryInstantiationException("Surgery was not ready yet!");
	}

	@Override
	public TreatmentFactory newInstance() {
		return new SurgeryFactory();
	}
}