package treatment;

import result.Result;
import scheduler.ScheduledTask;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidResultException;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment implements TreatmentIN
{

	ScheduledTask scheduledTask;
	private long duration;
	protected Result result;

	/**
	 * Default constructor.
	 * 
	 * @param treatmentName
	 *            The name of this treatment.
	 */
	public Treatment(long duration) {
		this.duration = duration;
	}

	public long getDuration() {
		return duration;
	}

	public void setResult(Result r) throws InvalidResultException {
		if(! this.isValidResult(result))
			throw new InvalidResultException("Invalid result given to Treatment");
		this.result = r;
	}
	
	/**
	 * @return True if r is a valid Result.
	 */
	private boolean isValidResult(Result r) {
		return r != null;
	}

	public void setScheduled(ScheduledTask task) {
		this.scheduledTask = task;
	}

}
