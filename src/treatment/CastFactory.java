package treatment;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidHospitalDateException;

public class CastFactory extends TreatmentFactory
{
	/**
	 * Default constructor. Package visible since it should only be used by Treatments.
	 */
	CastFactory() {}
	
	private String bodyPart_;
	private int castDuration_;

	/**
	 * Sets the body part.
	 * 
	 * @param bodyPart
	 * 			The body part.
	 */
	public void setBodyPart(String bodyPart) {
		this.bodyPart_ = bodyPart;
	}
	
	/**
	 * Checks whether the body part is valid.
	 * 
	 * @return
	 * 		True if the body part is valid.
	 */
	private boolean isValidBodyPart() {
		return !(bodyPart_ == null || bodyPart_.isEmpty());
	}
	
	/**
	 * Sets the duration of the cast.
	 * 
	 * @param castDuration
	 * 			The duration of the cast.
	 */
	public void setDuration(int castDuration) {
		this.castDuration_ = castDuration;
	}
	
	/**
	 * Checks whether the duration is valid.
	 * 
	 * @return True if the duration is valid.
	 */
	private boolean isValidDuration() {
		return castDuration_ > 0;
	}
	
	/**
	 * Checks whether the factory is ready for production.
	 * 
	 * @return True if the bodyPart and the duration is valid.
	 */
	protected boolean isReady() {
		return super.isReady() && isValidBodyPart() && isValidDuration();
	}
	
	/**
	 * Creates a Cast built from the given information.
	 * 
	 * @return A Cast built from the given information.
	 * @throws FactoryInstantiationException
	 *             The factory was not ready yet.
	 * @throws InvalidHospitalDateException
	 *             The used creationDate was invalid.
	 */
	@Override
	public Treatment create() throws FactoryInstantiationException {
		if(isReady())
			return new Cast(patientFile_, creationDate_, bodyPart_, castDuration_);
		throw new FactoryInstantiationException("Cast was not ready yet!");
	}

}
