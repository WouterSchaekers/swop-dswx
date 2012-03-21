package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import warehouse.item.PlasterType;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.CastIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidBodyPartException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLengthException;

/**
 * This class represents a treatment by cast.
 */
public class Cast extends Treatment implements CastIN
{
	private String bodyPart_;
	private int length_;
	public final static long DURATION_ = 2*HospitalDate.ONE_HOUR;

	/**
	 * Default constructor.
	 * 
	 * @param bodyPart
	 *            The bodypart on which the cast needs to be cast onto.
	 * @param length
	 *            The lengths of the cast.
	 * @throws InvalidHospitalDateException 
	 * @throws InvalidAmountException 
	 */
	public Cast(PatientFile patientFile, HospitalDate creationTime, String bodyPart, int length) throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, DURATION_, creationTime);
		this.bodyPart_ = bodyPart;
		this.length_ = length;
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
		return bodyPart_;
	}

	@Basic
	public void setBodyPart(String bodyPart) throws InvalidBodyPartException {
		if (!isValidBodyPart(bodyPart))
			throw new InvalidBodyPartException(
					"Invalid body part assigned to setBodyPart() in Cast!");
		this.bodyPart_ = bodyPart;
	}

	@Basic
	public int getLength() {
		return length_;
	}

	@Basic
	public void setLength(int length) throws InvalidLengthException {
		if (!isValidLength(length))
			throw new InvalidLengthException(
					"Invalid length assigned to setLength() in Cast!");
		this.length_ = length;
	}

	@Override
	public boolean hasFinished() {
		return false;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<PlasterType>(PlasterType.class,false));
		requirements.add(new RequirementType<Nurse>(Nurse.class,true));
		return requirements;
	}
}