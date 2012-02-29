package medicaltest;

import machine.MachinePool;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import scheduler.task.unscheduled.tests.UnscheduledMedicalTest;
import scheduler.task.unscheduled.tests.UnscheduledUltraSound;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScan extends MedicalTest
{

	public final static long DURATION = 30 * HospitalDate.ONE_MINUTE;
	private final String scaninfo;
	private final boolean recordVid;
	private final boolean recordImages;
	private Result result;

	public String getScaninfo() {
		return scaninfo;
	}

	public boolean hasVideoRecordingEnabled() {
		return recordVid;
	}

	public boolean hasImageRecordingEnabled() {
		return recordImages;
	}

	/**
	 * Default constructor.
	 * 
	 * @param recordImages
	 * @param recordVid
	 * @param scaninfo
	 * @throws InvalidTimeSlotException
	 * @see MedicalTest("UltraSoundScan")
	 */
	UltraSoundScan(String scaninfo, boolean recordVid, boolean recordImages)
			throws InvalidNameException, InvalidDurationException,
			InvalidTimeSlotException {
		super(UltraSoundScan.DURATION);
		this.scaninfo = scaninfo;
		this.recordVid = recordVid;
		this.recordImages = recordImages;
	}

	@Override
	public void setResult(Result r) {
		this.result = r;

	}

	@Override
	public UnscheduledMedicalTest getUnscheduled(UserManager userm,
			Warehouse warehouse, PatientFile file, TimeLord systemtime,
			TaskManager taskmanager, MachinePool pool)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		return new UnscheduledUltraSound(file, systemtime.getSystemTime(),
				userm, pool, this);
	}

	@Override
	public String appointmentInfo() {
		String rv = "";
		rv += "Ultra Sound Scan \n";
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

	public Result getResult() {
		return result;
	}

}
