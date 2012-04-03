package treatment;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidHospitalDateException;

public class CastFactory extends TreatmentFactory
{
	/**
	 * Default constructor. Package visible as it should only be used by Treatments.
	 */
	CastFactory() {}
	
	private String bodyPart_;
	private int length_;

	public void setBodyPart(String bodyPart) {
		this.bodyPart_ = bodyPart;
	}
	
	private boolean isValidBodyPart() {
		return !(bodyPart_ == null || bodyPart_.isEmpty());
	}
	
	public void setLength(int length) {
		this.length_ = length;
	}
	
	private boolean isValidLength() {
		return length_ > 0;
	}
	
	protected boolean isReady() {
		return super.isReady() && isValidBodyPart() && isValidLength();
	}
	
	@Override
	public Treatment create() throws FactoryInstantiationException, InvalidHospitalDateException {
		if(isReady())
			return new Cast(patientFile_, creationDate_, bodyPart_, length_);
		throw new FactoryInstantiationException("Cast was not ready yet!");
	}

}
