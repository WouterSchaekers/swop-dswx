package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import warehouse.item.MiscType;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.SurgeryIN;

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment implements SurgeryIN
{
	private String description_;
	public final static long DURATION_ = HospitalDate.ONE_MINUTE * 180;

	/**
	 * Default constructor. Package visible since it should only be used by the factories.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param creationTime
	 *            The date on which this description has been created.
	 * @param description
	 *            The description of the surgery.
	 */
	Surgery(PatientFile patientFile, HospitalDate creationTime, String description) {
		super(patientFile, DURATION_, creationTime);
		this.description_ = description;
	}

	/**
	 * @return The description of the surgery.
	 */
	@Basic
	public String getDescription() {
		return description_;
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
		requirements.add(new RequirementType<MiscType>(MiscType.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}
}