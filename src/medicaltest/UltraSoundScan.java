package medicaltest;

import machine.MachinePool;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import schedulerold.task.unscheduled.tests.UnscheduledMedicalTest;
import schedulerold.task.unscheduled.tests.UnscheduledUltraSound;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.*;

public class UltraSoundScan extends MedicalTest
{

	public final static long DURATION = 30 * HospitalDate.ONE_MINUTE;
	private final String scaninfo;
	private final boolean recordVid;
	private final boolean recordImages;

	UltraSoundScan(String scaninfo, boolean recordVid, boolean recordImages)
			throws InvalidDurationException {
		super(UltraSoundScan.DURATION);
		this.scaninfo = scaninfo;
		this.recordVid = recordVid;
		this.recordImages = recordImages;
	}

	public String getScaninfo() {
		return scaninfo;
	}

	public boolean hasVideoRecordingEnabled() {
		return recordVid;
	}

	public boolean hasImageRecordingEnabled() {
		return recordImages;
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

	@Override
	public UnscheduledMedicalTest getUnscheduled(UserManager userm,
			Warehouse warehouse, PatientFile file, TimeLord systemTime,
			MachinePool pool) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		return new UnscheduledUltraSound(file, systemTime.getSystemTime(), userm, pool, this);
	}
}
