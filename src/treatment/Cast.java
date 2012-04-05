package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import scheduler.requirements.WarehouseItemCondition;
import users.Nurse;
import warehouse.Warehouse;
import warehouse.item.PlasterType;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a treatment by cast.
 */
public class Cast extends Treatment
{
	private String bodyPart_;
	private int castDuration_;
	public final static long DURATION_ = 2 * HospitalDate.ONE_HOUR;

	/**
	 * Default constructor. Package visible since it should only be used by the
	 * factories.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param bodyPart
	 *            The bodypart on which the cast needs to be cast onto.
	 * @param castDuration
	 *            The duration of this treatment.
	 * @param diagnose
	 *            The diagnose of this cast.
	 * @param warehouse
	 *            The warehouse where the materials for this cast will come
	 *            from.
	 * @param warehouse
	 */
	Cast(PatientFile patientFile, HospitalDate creationDate, String bodyPart, int castDuration, Diagnose diagnose) {
		super(patientFile, diagnose, DURATION_, creationDate);
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
		requirements.add(new WarehouseItemCondition(new PlasterType(), warehouse_, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}
}