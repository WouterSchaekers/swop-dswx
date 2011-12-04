package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import task.Resource;

/**
 * Scheduled element class. This class represents the result of a request to
 * schedule a collection of requirements. The scheduler will have this set of
 * resources scheduled and the resources in this collection will be reserved
 * from startTime to endTime.
 * 
 */
public class ScheduledElement
{
	/**
	 * All the resources allocated for this scheduled element.
	 */
	private Collection<Resource> resources;
	/**
	 * The start moment that the scheduler assigned to this set of resources.
	 */
	private Date startTime;
	/**
	 * The end time that the scheduler assigned to this set of resources.
	 */
	@SuppressWarnings("unused")
	private Date endTime;

	/**
	 * Creates a scheduled element, normally only called in scheduler.
	 * 
	 * @param resources
	 *            The resources that have been allocated by the scheduler.
	 * @param startTime
	 *            The start time at which these resources have been allocated.
	 * @param endTime
	 *            The end time, the time at which these resources will no longer
	 *            be allocated in the scheduler.
	 */
	public ScheduledElement(Collection<Resource> resources, Date startTime,
			Date endTime) {
		this.resources = resources;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * A collection of resources that have been scheduled and are contained in
	 * this Object.
	 * 
	 * @return
	 */
	public Collection<Resource> getResources() {
		return new ArrayList<Resource>(resources);
	}

	/**
	 * The start time
	 * 
	 * @return
	 */
	public Date getStartTime() {
		return new Date(startTime.getTime());
	}

	public Date getEndTime() {
		return new Date();
	}

}
