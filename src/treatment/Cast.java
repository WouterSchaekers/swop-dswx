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
import exceptions.InvalidHospitalDateException;

/**
 * This class represents a treatment by cast.
 */
public class Cast extends Treatment implements CastIN
{
	private String bodyPart_;
	private int castDuration_;
	public final static long DURATION_ = 2 * HospitalDate.ONE_HOUR;

	/**
	 * Default constructor.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param bodyPart
	 *            The bodypart on which the cast needs to be cast onto.
	 * @param castDuration
	 *            The duration of this treatment.
	 * @throws InvalidHospitalDateException
	 *             The given creationDate was not valid.
	 */
	Cast(PatientFile patientFile, HospitalDate creationTime, String bodyPart, int castDuration) {
		super(patientFile, DURATION_, creationTime);
		this.bodyPart_ = bodyPart;
		this.castDuration_ = castDuration;
	}
	
	/**
	 * @return The duration of the cast.
	 */
	@Basic
	public int getCastDuration() {
		return castDuration_;
	}

	/**
	 * @return The bodypart.
	 */
	@Basic
	public String getBodyPart() {
		return bodyPart_;
	}

	/**
	 * Returns all the requirements that are needed to forfill this task.
	 * 
	 * @return All the requirements that are needed to forfill this task.
	 */
	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new RequirementType<PlasterType>(PlasterType.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}
}