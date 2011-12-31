package treatment;

import controllers.interfaces.CastIN;
import result.Result;
import scheduler.HospitalDate;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.*;

/**
 * This class represents a treatment by cast.
 */
public class Cast extends Treatment implements CastIN
{
	private String bodyPart;
	private int length;

	/**
	 * Default constructor.
	 * 
	 * @param bodyPart
	 *            The bodypart on which the cast needs to be cast onto.
	 * @param length
	 *            The lengths of the cast.
	 */
	public Cast(String bodyPart, int length) {
		super(2*HospitalDate.ONE_HOUR);
	}
	
	/**
	 * @return True if b is a valid body part.
	 */
	private boolean isValidBodyPart(String b) {
		return !b.equals("");
	}
	
	/**
	 * @return True if lengths is a valid cast length.
	 */
	private boolean isValidLength(int length) {
		return length > 0;
	}
	
	@Basic
	public String getBodyPart() {
		return bodyPart;
	}

	@Basic
	public void setBodyPart(String bodyPart) throws InvalidBodyPartException {
		if(!isValidBodyPart(bodyPart)) 
			throw new InvalidBodyPartException("Invalid body part assigned to setBodyPart() in Cast!");
		this.bodyPart = bodyPart;
	}

	@Basic
	public int getLength() {
		return length;
	}

	@Basic
	public void setLength(int length) throws InvalidLengthException {
		if(!isValidLength(length))
			throw new InvalidLengthException("Invalid length assigned to setLength() in Cast!");
		this.length = length;
	}

	@Override
	public void setResult(Result r) {
		
	}

	@Override
	public boolean hasFinished() {
		return false;
	}
	


}