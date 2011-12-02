package scheduler.constraints;

import java.util.ArrayList;
import java.util.Collection;

public class DomainTimeConstraints
{
	
	private final Collection<TimeConstraint> timeconstraints=new ArrayList<TimeConstraint>();
	public DomainTimeConstraints()
	{
		timeconstraints.add(new NurseWorkingHour());
	}
}
