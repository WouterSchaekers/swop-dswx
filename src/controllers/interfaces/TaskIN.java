package controllers.interfaces;

import java.util.Collection;
import scheduler.Schedulable;
import scheduler.tasks.TaskDescription;
import system.Location;

@controllers.PUBLICAPI
public interface TaskIN
{

@controllers.PUBLICAPI
	Collection<Schedulable> getResources();

@controllers.PUBLICAPI
	public <T extends TaskDescription> T getDescription();

@controllers.PUBLICAPI
	public boolean isFinished();

@controllers.PUBLICAPI
	public boolean isQueued();

@controllers.PUBLICAPI
	public boolean isScheduled();

@controllers.PUBLICAPI
	Location getLocation();
	
	
}
