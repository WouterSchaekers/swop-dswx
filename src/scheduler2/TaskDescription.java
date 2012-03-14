package scheduler2;

import java.util.Collection;

public abstract class TaskDescription
{
	public abstract Collection<Requirement> getAllRequirements();
	public abstract boolean isReady();
}