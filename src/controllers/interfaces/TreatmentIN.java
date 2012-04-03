package controllers.interfaces;

/**
 * This is the public interface for the Treatment class.
 */
@controllers.PUBLICAPI
public interface TreatmentIN
{

	/**
	 * Returns whether the Treatment has finished or not.
	 * 
	 * @return True if the Treatment has finished.
	 */
	@controllers.PUBLICAPI
	boolean hasFinished();
}