package medicaltest;

import scheduler.HospitalDate;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidDurationException;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	/**
	 * amount of times an analysis has to be run
	 */
	private final int amount;
	/**
	 * The focus of this bloodanalysis
	 */
	private final String focus;
	public final static long DURATION = 45 * HospitalDate.ONE_MINUTE;
	
	BloodAnalysis(int amount, String focus) throws InvalidDurationException {
		super(BloodAnalysis.DURATION);
		this.amount = amount;
		this.focus = focus;
	}


	@Basic
	public int getAmount() {
		return amount;
	}

	@Basic
	public String getFocus() {
		return this.focus;
	}

	/**
	 * Method to see the schedule state of this object
	 */
	@Override
	public String appointmentInfo() {
		String rv = "";
		rv += "Blood analysis \n";
		if (getScheduledTask() != null) {
			rv += "for \t:\t" + getScheduledTask().getPatient().getName()
					+ "\n";
			rv += "at \t:\t" + getScheduledTask().getTimeSlot().getStartPoint()
					+ "\ttill\t"
					+ getScheduledTask().getTimeSlot().getStopPoint();
		} else {
			rv += "Scan is not yet Scheduled";
		}
		return rv;
	}

}