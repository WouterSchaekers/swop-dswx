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
import exceptions.InvalidAmountException;
import exceptions.InvalidDescriptionException;
import exceptions.InvalidHospitalDateException;

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment implements SurgeryIN
{
	private String description = "";

	/**
	 * Default constructor.
	 * 
	 * @param description
	 *            The description of this surgery.
	 * @throws InvalidDescriptionException
	 * @throws InvalidHospitalDateException 
	 * @throws InvalidAmountException 
	 */
	public Surgery(PatientFile patientFile, HospitalDate creationTime, String description) throws InvalidDescriptionException, InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, HospitalDate.ONE_MINUTE * 180, creationTime);
		setDescription(description);
	}

	@Basic
	public String getDescription() {
		return description;
	}

	@Basic
	public void setDescription(String description)
			throws InvalidDescriptionException {
		if (!isValidDescription(description))
			throw new InvalidDescriptionException(
					"Invalid description given in setDescription() of Surgery!");
		this.description = description;
	}

	/**
	 * @return True if b is a valid description.
	 */
	private boolean isValidDescription(String d) {
		return !d.equals("");
	}

	@Override
	public boolean hasFinished() {
		return false;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(),false));
		requirements.add(new RequirementType<MiscType>(MiscType.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}
}