package scheduler.task.unscheduled.treatment;

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
import treatment.Medication;
import users.UserManager;

public class UnscheduledMedecation extends UnscheduledTreatment
{

	
	public UnscheduledMedecation(long duration, HospitalDate creationTime,
			Collection<Requirement> requirements, boolean backToBack,
			Diagnose diagnose,Medication med,UserManager um) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(duration, creationTime, requirements, backToBack, diagnose, um);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public long getExtraTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		// TODO Auto-generated method stub
		return null;
	}

}
