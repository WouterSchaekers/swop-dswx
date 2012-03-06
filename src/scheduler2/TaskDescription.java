package scheduler2;

import java.util.Collection;

public abstract class TaskDescription
{
	public abstract Collection<Requirement> getAllRequireMents();
	public abstract boolean isReady();
}
