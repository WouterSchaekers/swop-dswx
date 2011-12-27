package scheduler.task.unscheduled.treatment;

import java.util.ArrayList;
import java.util.LinkedList;
import patient.Diagnose;
import scheduler.HospitalDate;
import scheduler.task.Requirement;
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
	private Cast cast;
	private UserManager um;
	private Warehouse warehouse;

	/**
	 * 
	 * @param duration
	 * @param creationTime
	 * @param backToBack
	 * @param dependend
	 * @throws InvalidResourceException
	 * @throws InvalidDurationException
	 * @throws InvalidOccurencesException
	 * @throws InvalidAmountException
	 * @throws InvalidHospitalDateException
	 */
	public UnscheduledCast(long duration, HospitalDate creationTime,
			boolean backToBack, Diagnose dependendend, Cast cast,
			UserManager manager, Warehouse warehouse) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(duration, creationTime, new ArrayList<Requirement>(), backToBack,
				dependendend, manager);
		this.cast = cast;
		this.warehouse = warehouse;
	}

	@Override
	public long getExtraTime() {
		return 0;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> lin = new LinkedList<Integer>();
		lin.add(1);
		return lin;
	}

	public Cast getCast() {
		return cast;
	}

	@Override
	public boolean canBeScheduled() {
		return warehouse.hasPlaster(1);
	}
}
