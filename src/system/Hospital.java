package system;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import medicaltest.MedicalTestFactory;
import patient.PatientFileManager;
import scheduler.Schedulable;
import scheduler.TimeLord;
import scheduler.tasks.TaskManager;
import users.HospitalAdmin;
import users.UserManager;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidCampusException;

/**
 * This class represents a hospital. It can be used to pass a bunch of data to
 * the controllers in a fashionable way.
 */
public class Hospital
{
	private UserManager userManager_;
	private PatientFileManager patientFileManager_;
	private TaskManager taskManager_;
	private TimeLord systemTime_;
	private Collection<Campus> campusses_;
	private Collection<MedicalTestFactory> medicalTestFactories_;



	/**
	 * Constructor that creates a new hospital with the given data as initial
	 * equipment.
	 * 
	 * @param timeLord
	 *            The TimeLord to be associated with this hospital.
	 * @param userManager
	 *            The UserManager that's associated with this hospital.
	 * @param machinePool
	 *            The MachinePool of this hospital.
	 * @param patientFileManager
	 *            The PatientFileManager for this hospital.
	 * @param scheduler
	 *            The Scheduler for this hospital.
	 * @param taskManager
	 *            The TaskManager for this hospital.
	 * @param warehouse
	 *            The Warehouse for this hospital.
	 * @throws
	 */
	public Hospital(TimeLord timeLord, UserManager userManager, PatientFileManager patientFileManager,
			 TaskManager taskManager, HospitalAdmin hospitalAdmin) {
		this.systemTime_ = timeLord;
		this.userManager_ = userManager;
		this.patientFileManager_ = patientFileManager;
		this.taskManager_ = taskManager;
		this.campusses_ = new LinkedList<Campus>();
	}
	/**
	 * Method for a campus to make sure the double bind is made.
	 * @param campus
	 */
	void addCampus(Campus campus)
	{
		this.campusses_.add(campus);
	}
	@Basic
	public TimeLord getTimeKeeper() {
		return systemTime_;
	}

	@Basic
	public UserManager getUserManager() {
		return userManager_;
	}

	@Basic
	public PatientFileManager getPatientFileManager() {
		return patientFileManager_;
	}

	@Basic
	public TaskManager getTaskManager() {
		return taskManager_;
	}

	@Basic
	public Campus getCampus(String name) {
		for (Campus campus : campusses_) {
			if (campus.getCampusName().equals(name)) {
				return campus;
			}
		}
		throw new IllegalArgumentException("Campus does not exists");
	}

	public Collection<Location> getAllLocations() {
		return new LinkedList<Location>(this.campusses_);
	}

	public Collection<Campus> getAllCampuses() {
		return new LinkedList<Campus>(this.campusses_);
	}

	/**
	 * Removes a campus from this hospital.
	 */
	public void removeCampus(String name) {
		LinkedList<Campus> newCP = new LinkedList<Campus>();
		for (Campus c : this.campusses_)
			if ((c.getCampusName().equals(name)))
				newCP.add(c);
	}

	/**
	 * Vieze methode bah
	 * 
	 * @return
	 */
	public Collection<Schedulable> getAllSchedulables() {
		ArrayList<Object> sched = new ArrayList<Object>();
		for (Object o : userManager_.getAllUsers())
			sched.add(o);
		for (Object o : patientFileManager_.getAllPatientFiles())
			sched.add(o);
		for (Campus c : campusses_)
			for (Schedulable s : c.getSchedulables())
				sched.add(s);
		Collection<?> c = Collections.filter(sched, schedulableFilter());
		Collection<Schedulable> rv = new ArrayList<Schedulable>();
		for (Object o : c)
			rv.add((Schedulable) o);
		return rv;
	}

	private static final Filter schedulableFilter() {
		return new Filter()
		{
			@Override
			public <T> boolean allows(T arg) {
				return arg instanceof Schedulable;
			}
		};
	}

	public Collection<MedicalTestFactory> getMedicalTests() {
		return clonemedicalTestFactories();

	}

	public void addMedicalTestFactory(MedicalTestFactory fact) {
		medicalTestFactories_.add(fact);
	}

	private Collection<MedicalTestFactory> clonemedicalTestFactories() {
		Collection<MedicalTestFactory> rv = new ArrayList<MedicalTestFactory>();
		for (MedicalTestFactory fact : medicalTestFactories_)
			rv.add(fact.newInstance());
		return rv;

	}
}