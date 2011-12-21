package result;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidReportException;

/**
 * This class represent the result of a Medication.
 */
public class MedicationResult extends Result
{
	private boolean abnormalReaction;

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param report
	 *            The report of the Medication.
	 * @param abnormalReaction
	 *            Whether or not the patient who has been given the medication
	 *            has had an abnormal reacton.
	 * @throws InvalidReportException
	 */
	public MedicationResult(String report, boolean abnormalReaction) throws InvalidReportException{
		super(report);
		this.abnormalReaction = abnormalReaction;
	}

	@Basic
	public boolean getAbnormalReaction() {
		return this.abnormalReaction;
	}

}
