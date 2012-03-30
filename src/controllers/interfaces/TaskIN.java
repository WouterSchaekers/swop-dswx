package controllers.interfaces;

import java.util.Collection;
import scheduler.Schedulable;
import scheduler.tasks.TaskDescription;
import system.Location;

public interface TaskIN
{

	public void init();

	Collection<Schedulable> getResources();
	
	public <T extends TaskDescription> T getDescription();

	public boolean isFinished();

	public boolean isQueued();

	public boolean isScheduled();

	Location getLocation();
	
	
}
