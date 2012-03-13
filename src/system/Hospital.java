package system;

import java.util.LinkedList;
import patient.PatientFileManager;
import scheduler.Scheduler;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import users.HospitalAdmin;
import users.UserManager;
import warehouse.Warehouse;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a hospital. It can be used to pass a bunch of data to
 * the controllers in a fashionable way.
 */
public class Hospital
{
	private final UserManager _usm;
	private final PatientFileManager _pfm;
	private final TaskManager _tm;
	private final Scheduler _sdl;
	private final Warehouse _wh;
	private final TimeLord _st;
	private LinkedList<Campus> _cp;
	private final HospitalAdmin _hta;

	/**
	 * Initializes an empty hospital with a hospital admin and 2 campusses.
	 */
	public Hospital() {
		try {
			this._st = new TimeLord();
			this._usm = new UserManager();
			this._pfm = new PatientFileManager();
			this._sdl = new Scheduler(this._st);
			this._tm = new TaskManager(this._sdl);
			this._wh = new Warehouse();
			this._cp = new LinkedList<Campus>();
			this._hta = new HospitalAdmin("admin");
			this._usm.addUser(this._hta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Error();
		}
	}

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
			Scheduler scheduler, TaskManager taskManager, Warehouse warehouse,
			HospitalAdmin hospitalAdmin) {
		this._st = timeLord;
		this._usm = userManager;
		this._pfm = patientFileManager;
		this._sdl = scheduler;
		this._tm = taskManager;
		this._wh = warehouse;
		this._cp = new LinkedList<Campus>();
		try {
			this._hta = new HospitalAdmin("admin");
			this._usm.addUser(this._hta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Error();
		}
		
	}

	@Basic
	public TimeLord getSystemTime() {
		return _st;
	}

	@Basic
	public UserManager getUserManager() {
		return _usm;
	}

	@Basic
	public PatientFileManager getPatientFileManager() {
		return _pfm;
	}

	@Basic
	public TaskManager getTaskManager() {
		return _tm;
	}

	@Basic
	public Scheduler getScheduler() {
		return _sdl;
	}

	@Basic
	public Warehouse getWarehouse() {
		return _wh;
	}
	
	public Campus getCampus(int i){
		return _cp.get(i);
	}
	
	@Basic
	public HospitalAdmin getHospitalAdmin() {
		return this._hta;
	}
}