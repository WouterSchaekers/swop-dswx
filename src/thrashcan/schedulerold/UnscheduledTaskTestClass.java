package thrashcan.schedulerold;

import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

/**
 * Class created for testing purposes only! DO NOT USE THIS!
 */
public class UnscheduledTaskTestClass extends UnscheduledTask
{
	private LinkedList<Schedulable> requirements;
	private LinkedList<LinkedList<Schedulable>> schedulables;
	private LinkedList<Integer> occurences;

	public UnscheduledTaskTestClass(long duration, HospitalDate creationTime,
			long extraTime, LinkedList<Schedulable> r,
			LinkedList<LinkedList<Schedulable>> s, LinkedList<Integer> occ,
			boolean backToBack) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidNameException {
		super(new PatientFile("Dieter"), duration, creationTime, extraTime,
				backToBack);
		this.requirements = r;
		this.schedulables = s;
		this.occurences = occ;
	}

	public LinkedList<Schedulable> getRequirements() {
		return this.requirements;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> allSchedulables = new LinkedList<LinkedList<Schedulable>>();
		for (int i = 0; i < requirements.size(); i++) {
			LinkedList<Schedulable> tempList = new LinkedList<Schedulable>();
			tempList.add(requirements.get(i));
			allSchedulables.add(tempList);
		}
		allSchedulables.addAll(this.schedulables);
		return allSchedulables;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> allOccurences = new LinkedList<Integer>();
		for (int i = 0; i < requirements.size(); i++) {
			allOccurences.add(1);
		}
		allOccurences.addAll(this.occurences);
		return allOccurences;
	}

	@Override
	public boolean canBeScheduled() {
		return false;
	}

	@Override
	public HospitalDate getFirstSchedulingDateSince(HospitalDate hospitalDate) {
		return hospitalDate;
	}

	@Override
	public void setScheduled(ScheduledTask task) {

	}
}