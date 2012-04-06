package system;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import medicaltest.MedicalTestFactory;
import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeLord;
import scheduler.tasks.TaskManager;
import treatment.TreatmentFactory;
import users.HospitalAdmin;
import users.UserManager;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.HospitalIN;

/**
 * This class represents a hospital. It can be used to pass a bunch of data to
 * the controllers in a fashionable way.
 */
public class Hospital implements HospitalIN
{
	private UserManager userManager_;
	private PatientFileManager patientFileManager_;
	private TaskManager taskManager_;
	private TimeLord systemTime_;
	private Collection<Campus> campuses_;
	private Collection<MedicalTestFactory> medicalTestFactories_;
	private Collection<TreatmentFactory> treatmentFactories_;
	/**
	 * Represents the travel time between two campusses.
	 */
	public static final long TRAVEL_TIME = 15 * HospitalDate.ONE_MINUTE;

	/**
	 * Constructor that creates a new hospital with the given data as initial
	 * equipment.
	 * 
	 * @param timeLord
	 *            The TimeLord to be associated with this hospital.
	 * @param userManager
	 *            The UserManager that's associated with this hospital.
	 * @param patientFileManager
	 *            The PatientFileManager for this hospital.
	 * @param hospitalAdmin
	 *            The Admin of the Hospital.
	 * @param tsmb
	 *            The Builder that will build the TaskManager for this Hospital.
	 */
	public Hospital(TimeLord timeLord, UserManager userManager, PatientFileManager patientFileManager,
			HospitalAdmin hospitalAdmin, TaskManagerBuilder tsmb) {
		this.systemTime_ = timeLord;
		this.userManager_ = userManager;
		this.patientFileManager_ = patientFileManager;
		this.campuses_ = new LinkedList<Campus>();
		this.taskManager_ = tsmb.create(this);
		this.medicalTestFactories_ = new LinkedList<MedicalTestFactory>();
		this.treatmentFactories_ = new LinkedList<TreatmentFactory>();
	}

	/**
	 * Method for a campus to make sure the double bond is made.
	 * 
	 * @param campus
	 *            The Campus that has to be added to the Hospital.
	 */
	void addCampus(Campus campus) {
		this.campuses_.add(campus);
	}

	/**
	 * @return The current SystemTime.
	 */
	@Basic
	public TimeLord getTimeKeeper() {
		return this.systemTime_;
	}

	/**
	 * @return The UserManager.
	 */
	@Basic
	public UserManager getUserManager() {
		return this.userManager_;
	}

	/**
	 * @return The PatientFileManager.
	 */
	@Basic
	public PatientFileManager getPatientFileManager() {
		return this.patientFileManager_;
	}

	/**
	 * @return The TaskManager.
	 */
	@Basic
	public TaskManager getTaskManager() {
		return this.taskManager_;
	}

	/**
	 * Returns the Campus that has a given name.
	 * 
	 * @param name
	 *            The name of the Campus.
	 * @return The Campus with the given name.
	 */
	public Campus getCampus(String name) {
		for (Campus campus : this.campuses_)
			if (campus.getCampusName().equals(name))
				return campus;
		throw new IllegalArgumentException("Campus does not exists");
	}

	/**
	 * @return A collection of all Locations in this Hospital.
	 */
	public Collection<Location> getAllLocations() {
		return new LinkedList<Location>(this.campuses_);
	}

	/**
	 * @return A collection of all Campusses in this Hospital.
	 */
	public Collection<Campus> getAllCampuses() {
		return new LinkedList<Campus>(this.campuses_);
	}

	/**
	 * Removes a campus from this hospital.
	 * 
	 * @param name
	 *            The Campus with the given name that has to be removed.
	 */
	public void removeCampus(String name) {
		LinkedList<Campus> newCP = new LinkedList<Campus>();
		for (Campus c : this.campuses_)
			if ((c.getCampusName().equals(name)))
				newCP.add(c);
	}

	/**
	 * @return A collection of all the resources in this Hospital.
	 */
	public Collection<Schedulable> getAllSchedulables() {
		ArrayList<Object> sched = new ArrayList<Object>();
		for (Object o : userManager_.getAllUsers())
			sched.add(o);
		for (PatientFile o : patientFileManager_.getAllPatientFiles())
			sched.add(o.getPatient());
		for (Campus c : campuses_)
			for (Schedulable s : c.getSchedulables())
				sched.add(s);
		Collection<?> c = Collections.filter(sched, schedulableFilter());
		Collection<Schedulable> rv = new ArrayList<Schedulable>();
		for (Object o : c)
			rv.add((Schedulable) o);
		return rv;
	}

	/**
	 * @return A filter that filters Schedulables.
	 */
	private static final Filter schedulableFilter() {
		return new Filter()
		{
			@Override
			public <T> boolean allows(T arg) {
				return arg instanceof Schedulable;
			}
		};
	}

	/**
	 * @return A collection of all MedicalTests.
	 */
	public Collection<MedicalTestFactory> getMedicalTests() {
		return clonemedicalTestFactories();

	}

	/**
	 * Adds a MedicalTestFactory.
	 * 
	 * @param fact
	 *            The MedicalTestFactory.
	 */
	public void addMedicalTestFactory(MedicalTestFactory fact) {
		this.medicalTestFactories_.add(fact);
	}

	/**
	 * @return A collection of all the MedicalTestFactories.
	 */
	private Collection<MedicalTestFactory> clonemedicalTestFactories() {
		Collection<MedicalTestFactory> rv = new ArrayList<MedicalTestFactory>();
		for (MedicalTestFactory fact : this.medicalTestFactories_)
			rv.add(fact.newInstance());
		return rv;

	}

	/**
	 * @return A collection of all the TreatmentFactories.
	 */
	public Collection<TreatmentFactory> getTreatments() {
		return cloneTreatmentFactories();

	}

	/**
	 * Adds a TreatmentFactory to the Hospital.
	 * 
	 * @param fact
	 *            The TreatmentFactory that has to be added.
	 */
	public void addTreatmentFactory(TreatmentFactory fact) {
		this.treatmentFactories_.add(fact);
	}

	/**
	 * @return A collection of all the TreatmentFactories.
	 */
	private Collection<TreatmentFactory> cloneTreatmentFactories() {
		Collection<TreatmentFactory> rv = new LinkedList<TreatmentFactory>();
		for (TreatmentFactory fact : treatmentFactories_)
			rv.add(fact.newInstance());
		return rv;
	}
}