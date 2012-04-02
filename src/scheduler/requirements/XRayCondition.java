package scheduler.requirements;

import java.util.Collection;
import java.util.LinkedList;
import medicaltest.XRayScan;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.StartTimePoint;
import scheduler.StopTimePoint;
import scheduler.TimeSlot;
import scheduler.tasks.Task;
import controllers.interfaces.TaskIN;

public class XRayCondition implements Requirement
{
	private PatientFile patientFile_;
	public static final int MAX_XRAYS_PER_YEAR = 10;

	public XRayCondition(PatientFile pf) {
		if (pf == null)
			throw new IllegalArgumentException(pf + " cannot be null!");
		this.patientFile_ = pf;
	}

	@Override
	public boolean backToBack() {
		return false;
	}

	@Override
	public void collect() {
		; // do nothing
	}

	@Override
	public int getAmount() {
		return 1;
	}

	@Override
	public boolean isCrucial() {
		return false;
	}

	@Override
	public boolean isMarkedForDeletion() {
		return false;
	}

	@Override
	public boolean isMetBy(Requirable requirable) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isMetOn(HospitalDate hospitalDate) {
		Collection<TaskIN> allTests = patientFile_.getAllMedicalTests();
		Collection<Task<XRayScan>> xrays = new LinkedList<Task<XRayScan>>();

		for (TaskIN task : allTests) {
			if (!task.isQueued() && task.getDescription() instanceof XRayScan)
				xrays.add((Task<XRayScan>) task);
		}

		return this.isPossibleAt(hospitalDate, xrays);
	}
	
	/**
	 * Checks if the patient kept in this condition can have another XRayScan at
	 * the requested date, concerning max amount of xrays per year.
	 * 
	 */
	private boolean isPossibleAt(HospitalDate date, Collection<Task<XRayScan>> tasks) {
		HospitalDate dummyBefore = new HospitalDate(date.getTimeSinceStart() - HospitalDate.ONE_YEAR);
		HospitalDate dummyAfter = new HospitalDate(date.getTimeSinceStart() - HospitalDate.ONE_YEAR);
		TimeSlot before = new TimeSlot(new StartTimePoint(dummyBefore), new StopTimePoint(date));
		TimeSlot after = new TimeSlot(new StartTimePoint(date), new StopTimePoint(dummyAfter));
		int countBefore = 0;
		int countAfter = 0;

		for (Task<XRayScan> task : tasks) {
			if (before.contains(task.getDate()))
				countBefore++;
			if (after.contains(task.getDate()))
				countAfter++;
		}
		return countBefore < MAX_XRAYS_PER_YEAR && countAfter < MAX_XRAYS_PER_YEAR;
	}
}