package result;

import exceptions.InvalidReportException;

/**
 * This class represents the result after a Cast has been applied.
 */
public class CastResult extends Result
{

	/**
	 * @see Result
	 */
	public CastResult(String report) throws InvalidReportException {
		super(report);
	}
}
