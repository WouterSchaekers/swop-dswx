package scheduler;

import java.util.Collection;
import java.util.Date;
import resources.Resource;

public class ScheduledElement
{
	private Collection<Resource> resources;
	private Date date;
	public ScheduledElement(Collection<Resource> resources, Date date){
		this.resources = resources;
		this.date = date;
	}
	
	public Collection<Resource> getResources(){
		return resources;
	}
	public Date getDate(){
		return date;
	}
}
