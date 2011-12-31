package medicaltest;

import machine.MachinePool;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import scheduler.task.unscheduled.tests.UnscheduledMedicalTest;
import scheduler.task.unscheduled.tests.UnscheduledXRayScan;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;

public class XRayScan extends MedicalTest
{
	public final static long DURATION = 15 * HospitalDate.ONE_MINUTE;
	private final String bodypart;

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

	private int num;
	private float zoomlevel;

	/**
	 * Default constructor.
	 * 
	 * @param zoomlevel
	 * @param num
	 * @throws InvalidTimeSlotException
	 * @see MedicalTest("XRayScan",15,patientFile)
	 */
	XRayScan(String bodypart, int num, float zoomlevel)
			throws InvalidNameException, InvalidDurationException,
			InvalidTimeSlotException {
		super(XRayScan.DURATION);
		this.bodypart = bodypart;
		this.num = num;
		this.zoomlevel = zoomlevel;
	}

	@Override
	public void setResult(Result r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UnscheduledMedicalTest getUnscheduled(UserManager userm,
			Warehouse warehouse, PatientFile file, TimeLord systemtime,
			TaskManager taskmanager, MachinePool pool)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		return new UnscheduledXRayScan(file, systemtime.getSystemTime(), userm, pool, this);
	}

	@Override
	public String appointMentInfo() {
		String rv = "";
		rv+="Xray Scan\n";
		if(getScheduledTask()!=null){
			rv+="for \t:\t"+getScheduledTask().getPatient().getName()+"\n";
			rv+="at \t:\t" + getScheduledTask().getTimeSlot().getStartPoint()+"\ttill\t"+getScheduledTask().getTimeSlot().getStopPoint();
		}
		return rv;
	}

}
