package controllers.interfaces;

import result.Result;

/**
 * This is the public interface for the Description class.
 */
public interface DescriptionIN
{
	/**
	 * Returns whether the Description needs a result.
	 * 
	 * @return True if the Description needs a result.
	 */
	public boolean needsResult();

	/**
	 * Sets the result of the Description.
	 * 
	 * @param result
	 *            The result of the Description.
	 */
	public void setResult(String result);

	/**
	 * Returns the Result of the Description.
	 * 
	 * @return The Result in the Description.
	 */
	public Result getResult();

	/**
	 * Returns the safe interface of the PatientFile.
	 * 
	 * @return The safe interface of the PatientFile.
	 */
	public PatientFileIN getPatientFile();
}