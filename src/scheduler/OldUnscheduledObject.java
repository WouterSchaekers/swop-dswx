package scheduler;

import java.util.LinkedList;
import patient.PatientFile;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import system.Hospital;

//TODO: is dit de moeite ffs 
public class OldUnscheduledObject extends UnscheduledTask
{
	public OldUnscheduledObject(UnscheduledTask task,Hospital state) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException
	{
		super(getpf(task,state),duration(task,state),getSysTime(task,state),getExtraTime(task,state),getBackToBackness(task,state));
	}
	private static boolean getBackToBackness(UnscheduledTask task,
			Hospital state) {
		// TODO Auto-generated method stub
		return false;
	}
	private static long getExtraTime(UnscheduledTask task, Hospital state) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static HospitalDate getSysTime(UnscheduledTask task,
			Hospital state) {
		// TODO Auto-generated method stub
		return null;
	}
	private static long duration(UnscheduledTask task, Hospital state) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static PatientFile getpf(UnscheduledTask task, Hospital state) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean canBeScheduled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HospitalDate getFirstSchedulingDateSince(HospitalDate hospitalDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScheduled(ScheduledTask task) {
		// TODO Auto-generated method stub

	}

}
