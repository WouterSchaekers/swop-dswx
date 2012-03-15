package medicaltest;

import machine.MachinePool;
import patient.PatientFile;
import result.Result;
import scheduler2.HospitalDate;
import scheduler2.TimeLord;
import schedulerold.task.unscheduled.tests.UnscheduledMedicalTest;
import schedulerold.task.unscheduled.tests.UnscheduledXRayScan;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.*;

public class XRayScan extends MedicalTest
{
	public final static long DURATION = 15 * HospitalDate.ONE_MINUTE;
	private final String bodypart;
	private int num;
	private float zoomlevel;

	XRayScan(String bodypart, int num, float zoomlevel)
			throws InvalidNameException, InvalidDurationException,
			InvalidTimeSlotException {
		super(XRayScan.DURATION);
		this.bodypart = bodypart;
		this.num = num;
		this.zoomlevel = zoomlevel;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getZoomlevel() {
		return zoomlevel;
	}

	public void setZoomlevel(float zoomlevel) {
		this.zoomlevel = zoomlevel;
	}

	public String getBodypart() {
		return bodypart;
	}

	@Override
	public void setResult(Result r) {

	}

	@Override
	public String appointmentInfo() {
		String rv = "";
		rv += "Xray Scan\n";
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
		return new UnscheduledXRayScan(file, systemTime.getSystemTime(), userm,
				pool, this);
	}

}
