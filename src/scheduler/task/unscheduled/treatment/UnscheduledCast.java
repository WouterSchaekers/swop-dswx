package scheduler.task.unscheduled.treatment;

import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import treatment.Cast;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public class UnscheduledCast extends UnscheduledTreatment
{
	private Warehouse warehouse;
	private Cast cast;

	/**
	 * @param p
	 * @param diagnose
	 * @param systemTime
	 * @param cast
	 * @param userManager
	 * @throws InvalidResourceException
	 * @throws InvalidDurationException
	 * @throws InvalidOccurencesException
	 * @throws InvalidAmountException
	 * @throws InvalidHospitalDateException
	 */
	public UnscheduledCast(PatientFile p, Diagnose diagnose,
			HospitalDate systemTime, Cast cast, UserManager userManager,
			Warehouse warehouse) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(p, diagnose, 2 * HospitalDate.ONE_HOUR, systemTime, userManager);
		this.cast = cast;
		this.warehouse = warehouse;
	}

	public Cast getCast() {
		return cast;
	}

	@Override
	public boolean canBeScheduled() {
		return super.canBeScheduled() && warehouse.hasPlaster(1);
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		rv.add(1);
		return rv;
	}
}