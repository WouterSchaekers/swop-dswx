package scheduler.task.unscheduled.treatment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import patient.Diagnose;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import scheduler.HospitalDate;
import scheduler.task.Requirement;
import scheduler.task.Schedulable;
import treatment.Cast;
public class UnscheduledCast extends UnscheduledTreatment
{
	private Cast cast;

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
			boolean backToBack,Diagnose dependendend,Cast cast) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(duration, creationTime,new ArrayList<Requirement>(), backToBack,dependendend);
		this.cast=cast;
		
	}

	@Override
	public long getExtraTime() {
		return 0;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> sched = new LinkedList<Schedulable>();
		rv.add(sched);
		
		return null;
	}

	@Override
	public Collection<Requirement> getRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		// TODO Auto-generated method stub
		return null;
	}
	public Cast getCast()
	{
		return cast;
	}
}
