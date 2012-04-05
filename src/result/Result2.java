package result;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidReportException;

/**
 * This class represents the result of a medical test or a treatment.
 */
public class Result2 implements Result
{
	private String report_ = "";

	/**
	 * Default constructor.
	 * 
	 * @param report
	 *            The report for this Result.
	 * @throws InvalidReportException
	 */
	public Result2(String report) throws InvalidReportException {
		if (!isValidReport(report))
			throw new InvalidReportException("Invalid report for Result!");
		this.report_ = report;
	}

	/**
	 * Returns the report of this result.
	 * 
	 * @return The report of this result.
	 */
	@Basic
	public String getReport() {
		return this.report_;
	}

	/**
	 * Checks whether the given string is a valid report.
	 * 
	 * @return True if s is a not null and not empty.
	 */
	protected boolean isValidReport(String s) {
		return s != null && !s.equals("");
	}
}
