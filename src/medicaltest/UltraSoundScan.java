package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import machine.UltraSoundScanner;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;

public class UltraSoundScan extends MedicalTest
{

	public final static long DURATION_ = 30 * HospitalDate.ONE_MINUTE;
	private final String scaninfo;
	private final boolean recordVid;
	private final boolean recordImages;

	UltraSoundScan(PatientFile patientFile, HospitalDate creationTime, String scaninfo, boolean recordVid, boolean recordImages)
			throws InvalidDurationException, InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, DURATION_, creationTime);
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
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<UltraSoundScanner>(UltraSoundScanner.class));
		return requirements;
	}
}