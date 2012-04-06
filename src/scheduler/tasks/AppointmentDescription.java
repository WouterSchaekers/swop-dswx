package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import patient.PatientFile;
import result.NoResultFactory;
import result.Result;
import result.ResultFactory;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Doctor;

/**
 * This class represents an Appointment.
 */
public class AppointmentDescription extends TaskDescriptionWithPatientFile
{
	private Doctor doctor;
	/**
	 * The duration of an AppointmentDescription.
	 */
	public final static long DURATION_ = 30 * HospitalDate.ONE_MINUTE;

	/**
	 * Default constructor.
	 * 
	 * @param doctor
	 *            The doctor that participates in the Appointment.
	 * @param patientFile
	 *            The patientFile that has the Appointment.
	 * @param creationDate
	 *            The date on which this description has been created.
	 */
	public AppointmentDescription(Doctor doctor, PatientFile patientFile, HospitalDate creationDate) {
		super(patientFile, AppointmentDescription.DURATION_, HospitalDate.ONE_HOUR, creationDate);
		this.doctor = doctor;
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
		requirements.add(new RequirementType<Doctor>(Doctor.class, true, 1));
		return requirements;
	}

	/**
	 * @return The doctor that participates in this Appointment.
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Initializes the Appointment. Does nothing.
	 * 
	 * @param task
	 *            The task that has to be initialized.
	 */
	@Override
	public <T extends TaskDescription> void initTask(Task<T> task) {
		;
	}

	/**
	 * Deinitializes the Appointment. Does nothing.
	 * 
	 * @param task
	 *            The task that has to be deinitialized.
	 */
	@Override
	public <T extends TaskDescription> void deInit(Task<T> task) {
		;
	}

	/**
	 * @return The result.
	 */
	public Result getResult() {
		return this.result_;
	}

	/**
	 * Returns the observables that observe this medical test.
	 */
	@Override
	public Collection<Observable> getObservables() {
		return new LinkedList<Observable>();
	}

	/**
	 * @return A NoResultFactory.
	 */
	@Override
	public ResultFactory getResultFactory() {
		return new NoResultFactory();
	}

	/**
	 * Returns null. Appointments don't have a Result.
	 */
	@Override
	public Result give(ResultFactory resultFactory) {
		return null;
	}
}