package result;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidReportException;

/**
 * This class represents the result of a medical testo r a treatment.
 */
public class Result
{
	private String report = "";

	/**
	 * Default constructor.
	 * 
	 * @param report
	 *            The report for this Result.
	 * @throws InvalidReportException
	 */
	public Result(String report) throws InvalidReportException {
		if (this.canHaveAsDetail(report))
			this.report = report;
		else
			throw new InvalidReportException("Invalid report for Result!");
	}

	@Basic
	public String getReport() {
		return report;
	}

	/**
	 * @return True if s is a valid detail for this Result.
	 */
	protected boolean canHaveAsDetail(String s) {
		return s != null && !s.equals("");
	}

	/**
	 * @return True if s is a valid amount for this Result.
	 */
	protected boolean canhaveAsAmount(int a) {
		return a >= 0;
	}
}
