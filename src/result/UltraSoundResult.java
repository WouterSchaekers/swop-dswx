package result;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidReportException;

/**
 * This class represent the result of an UltraSoundScan.
 */
public class UltraSoundResult extends Result
{
	private MassType massType;

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param report
	 *            The report of the UltraSound scan.
	 * @param massType
	 *            The type of the mass found.
	 * @throws InvalidReportException
	 */
	public UltraSoundResult(String report, MassType massType)
			throws InvalidReportException {
		super(report);
		this.massType = massType;
	}

	@Basic
	public MassType getMassType() {
		return this.massType;
	}

	/**
	 * This enumeration will keep the type of the mass found for this Result.
	 */
	private enum MassType
	{
		benign, malicious, unknown
	}
}
